package com.csci448.avelychko.mis_match.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.data.PhotographRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class PhotographViewModel(private val photographRepository: PhotographRepository) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.PhotographViewModel"
    }

    private val mPhotographListStateFlow: MutableStateFlow<List<Photograph>> = MutableStateFlow(emptyList())
    val photographListStateFlow: StateFlow<List<Photograph>>
        get() = mPhotographListStateFlow.asStateFlow()

    private val mPhotographIdStateFlow: MutableStateFlow<UUID> = MutableStateFlow(UUID.randomUUID())
    private val mCurrentPhotographStateFlow: MutableStateFlow<Photograph?> = MutableStateFlow(null)
    val currentPhotographStateFlow: StateFlow<Photograph?>
        get() = mCurrentPhotographStateFlow.asStateFlow()

    init {
        Log.d(LOG_TAG, "PhotographViewModel initializing")
        viewModelScope.launch {
            photographRepository.getPhotographs().collect { photographList ->
                Log.d(LOG_TAG, "collected ${photographList.size} photographs")
                mPhotographListStateFlow.update { photographList }
            }
        }
        viewModelScope.launch {
            mPhotographIdStateFlow
                .map { uuid -> photographRepository.getPhotograph(uuid) }
                .collect { photograph ->
                    Log.d(LOG_TAG, "collected photograph ${photograph?.id}")
                    mCurrentPhotographStateFlow.update { photograph }
                }
        }
    }

    override fun onCleared() {
        Log.d(LOG_TAG, "onCleared() called")
        viewModelScope.cancel()
        super.onCleared()
    }

    fun loadPhotographById(uuid: UUID) {
        Log.d(LOG_TAG, "loadPhotographById($uuid) called")
        mPhotographIdStateFlow.update { uuid }
    }

    fun addPhotograph(photograph: Photograph) {
        Log.d(LOG_TAG, "addPhotograph(${photograph.id}) called")
        photographRepository.addPhotograph(photograph)
    }

    fun deletePhotograph(photograph: Photograph) {
        Log.d(LOG_TAG, "deletePhotograph(${photograph.id}) called")
        if(photograph.id == mCurrentPhotographStateFlow.value?.id) {
            mCurrentPhotographStateFlow.update { null }
        }
        photographRepository.deletePhotograph(photograph)
    }
}