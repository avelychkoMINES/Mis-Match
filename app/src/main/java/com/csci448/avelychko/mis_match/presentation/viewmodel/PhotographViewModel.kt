package com.csci448.avelychko.mis_match.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.data.PhotographRepository
import com.csci448.avelychko.mis_match.data.Triplet
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class PhotographViewModel(private val photographRepository: PhotographRepository) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.PhotographViewModel"
    }

    val selectedTopState: MutableState<Photograph?> = mutableStateOf(null)
    val selectedBottomState: MutableState<Photograph?> = mutableStateOf(null)
    val selectedShoeState: MutableState<Photograph?> = mutableStateOf(null)
    var indexTop: Int = 0;
    var indexBottom: Int = 0;
    var indexShoe: Int = 0;

    var typeString = ""

    val selectedCameraState = MutableStateFlow("")

    val builderEnabled = mutableStateOf(false)
    val notificationsOn = mutableStateOf(false)

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

        if (getTopPhoto().isNotEmpty() && getBottomPhoto().isNotEmpty()
            && getShoePhoto().isNotEmpty()) {
            builderEnabled.value = true
        } else {
            builderEnabled.value = false
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

//    fun getTriplets(): List<Triplet> {
//        val tripletsLiveData = MutableLiveData<List<Triplet>>()
//
//        viewModelScope.launch {
//            try {
//                val triplets = photographRepository.getAllTriplets()
//                tripletsLiveData.value = triplets
//                Log.d(LOG_TAG, triplets.toString())
//            } catch (e: Exception) {
//                Log.e(LOG_TAG, e.toString())
//            }
//        }
//
//        if (tripletsLiveData.value == null) {
//            return emptyList()
//        }
//        else {
//            Log.d(LOG_TAG, "returning triplets")
//            return tripletsLiveData.value!!
//        }
//    }
    fun getTriplets(): Flow<List<Triplet>> = flow {
        try {
            val triplets = photographRepository.getAllTriplets()
            emit(triplets)
            Log.d(LOG_TAG, triplets.toString())
        } catch (e: Exception) {
            Log.e(LOG_TAG, e.toString())
        }
    }

    fun addTriplet(triplet: Triplet) {
        Log.d(LOG_TAG, "creating a triplet: $triplet")
        photographRepository.addTriplet(triplet)
    }

    fun deleteTriplet(triplet: Triplet) {
        Log.d(LOG_TAG, "deleting triplet: $triplet")
        photographRepository.deleteTriplet(triplet)
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


    private val mSnapshotFlow: MutableStateFlow<List<Photograph>> = MutableStateFlow(emptyList())
    val snapshotFlow: StateFlow<List<Photograph>> get() = mSnapshotFlow

    fun refresh() {
        viewModelScope.launch {
            val photographs = photographRepository.getTopPhoto()
            mSnapshotFlow.value = photographs
        }
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

    fun moveToNextTop() {
        indexTop++
        if (indexTop >= photographRepository.getTopPhoto().size) {
            indexTop = 0
        }
        selectedTopState.value = photographRepository.getTopPhoto()[indexTop]
    }

    fun moveToNextBottom() {
        indexBottom++
        if (indexBottom >= photographRepository.getBottomPhoto().size) {
            indexBottom = 0
        }
        selectedBottomState.value = photographRepository.getBottomPhoto()[indexBottom]
    }

    fun moveToNextShoe() {
        indexShoe++
        if (indexShoe >= photographRepository.getShoePhoto().size) {
            indexShoe = 0
        }
        selectedShoeState.value = photographRepository.getShoePhoto()[indexShoe]
    }

    fun moveToPrevTop() {
        indexTop--
        if (indexTop < 0) {
            indexTop = photographRepository.getTopPhoto().size - 1
        }
        selectedTopState.value = photographRepository.getTopPhoto()[indexTop]
    }

    fun moveToPrevBottom() {
        indexBottom--
        if (indexBottom < 0) {
            indexBottom = photographRepository.getBottomPhoto().size - 1
        }
        selectedBottomState.value = photographRepository.getBottomPhoto()[indexBottom]
    }

    fun moveToPrevShoe() {
        indexShoe--
        if (indexShoe < 0) {
            indexShoe = photographRepository.getShoePhoto().size - 1
        }
        selectedShoeState.value = photographRepository.getShoePhoto()[indexShoe]
    }

    fun setNotification(bool: Boolean) {
        notificationsOn.value = bool
    }
}
