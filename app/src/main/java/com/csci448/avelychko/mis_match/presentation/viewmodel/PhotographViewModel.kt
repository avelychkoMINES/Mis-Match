//package com.csci448.avelychko.mis_match.presentation.viewmodel
//
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import com.csci448.avelychko.mis_match.data.Photograph
//import com.csci448.avelychko.mis_match.data.PhotographRepository
//import com.csci448.avelychko.mis_match.data.database.Picture
//import kotlinx.coroutines.flow.MutableStateFlow
//
//class PhotographViewModel(val repo: PhotographRepository) {
//    val selectedTopState: MutableState<Picture?> = mutableStateOf(null);
//    val selectedBottomState: MutableState<Picture?> = mutableStateOf(null);
//    val selectedShoeState: MutableState<Picture?> = mutableStateOf(null);
//    var indexTop: Int = 0;
//    var indexBottom: Int = 0;
//    var indexShoe: Int = 0;
//
//
//    fun addPhotograph(photograph: Photograph) {
//        repo.addPhotograph(photograph)
//    }
//
//    fun moveToNextTop() {
//        indexTop++
//        if (indexTop >= topList.size) {
//            indexTop = 0
//        }
//        selectedTopState.value = topList[indexTop]
//    }
//
//    fun moveToNextBottom() {
//        indexBottom++
//        if (indexBottom >= bottomList.size) {
//            indexBottom = 0
//        }
//        selectedBottomState.value = bottomList[indexBottom]
//    }
//
//    fun moveToNextShoe() {
//        indexShoe++
//        if (indexShoe >= shoeList.size) {
//            indexShoe = 0
//        }
//        selectedShoeState.value = shoeList[indexShoe]
//    }
//
//    fun moveToPrevTop() {
//        indexTop--
//        if (indexTop < 0) {
//            indexTop = topList.size - 1
//        }
//        selectedTopState.value = topList[indexTop]
//    }
//
//    fun moveToPrevBottom() {
//        indexBottom--
//        if (indexBottom < 0) {
//            indexBottom = bottomList.size - 1
//        }
//        selectedBottomState.value = bottomList[indexBottom]
//    }
//
//    fun moveToPrevShoe() {
//        indexShoe--
//        if (indexShoe < 0) {
//            indexShoe = shoeList.size - 1
//        }
//        selectedShoeState.value = shoeList[indexShoe]
//    }
//}

package com.csci448.avelychko.mis_match.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    val selectedTopState: MutableState<Photograph?> = mutableStateOf(null);
    val selectedBottomState: MutableState<Photograph?> = mutableStateOf(null);
    val selectedShoeState: MutableState<Photograph?> = mutableStateOf(null);
    var indexTop: Int = 0;
    var indexBottom: Int = 0;
    var indexShoe: Int = 0;

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

    fun getTopPhoto(): MutableList<Photograph> {
        return photographRepository.getTopPhoto()
    }

    fun getBottomPhoto(): List<Photograph> {
        return photographRepository.getBottomPhoto()
    }

    fun getShoePhoto(): List<Photograph> {
        return photographRepository.getShoePhoto()
    }
//
//    fun moveToNextTop() {
//        indexTop++
//        if (indexTop >= topList.size) {
//            indexTop = 0
//        }
//        selectedTopState.value = topList[indexTop]
//    }
//
//    fun moveToNextBottom() {
//        indexBottom++
//        if (indexBottom >= bottomList.size) {
//            indexBottom = 0
//        }
//        selectedBottomState.value = bottomList[indexBottom]
//    }
//
//    fun moveToNextShoe() {
//        indexShoe++
//        if (indexShoe >= shoeList.size) {
//            indexShoe = 0
//        }
//        selectedShoeState.value = shoeList[indexShoe]
//    }
//
//    fun moveToPrevTop() {
//        indexTop--
//        if (indexTop < 0) {
//            indexTop = topList.size - 1
//        }
//        selectedTopState.value = topList[indexTop]
//    }
//
//    fun moveToPrevBottom() {
//        indexBottom--
//        if (indexBottom < 0) {
//            indexBottom = bottomList.size - 1
//        }
//        selectedBottomState.value = bottomList[indexBottom]
//    }
//
//    fun moveToPrevShoe() {
//        indexShoe--
//        if (indexShoe < 0) {
//            indexShoe = shoeList.size - 1
//        }
//        selectedShoeState.value = shoeList[indexShoe]
//    }
}
