package com.csci448.avelychko.mis_match.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.data.database.Picture
import kotlinx.coroutines.flow.MutableStateFlow

class MisMatchViewModel(val topList: List<Picture>, val bottomList: List<Picture>, val shoeList: List<Picture>) {
    private val mTopPictureList: List<Photograph> = emptyList()
    private val mBottomPictureList: List<Photograph> = emptyList()
    private val mShoesPictureList: List<Photograph> = emptyList()
    val selectedTopState: MutableState<Picture?> = mutableStateOf(null);
    val selectedBottomState: MutableState<Picture?> = mutableStateOf(null);
    val selectedShoeState: MutableState<Picture?> = mutableStateOf(null);
    var indexTop: Int = 0;
    var indexBottom: Int = 0;
    var indexShoe: Int = 0;


    fun addPhotograph(photograph: Photograph) {

    }
    
    fun moveToNextTop() {
        indexTop++
        selectedTopState.value = topList[indexTop]
    }
    
    fun moveToNextBottom() {
    }
    
    fun moveToNextShoe() {
    }
    
    fun moveToPrevTop() {
    }
    
    fun moveToPrevBottom() {
    }
    
    fun moveToPrevShoe() {
    }
}
