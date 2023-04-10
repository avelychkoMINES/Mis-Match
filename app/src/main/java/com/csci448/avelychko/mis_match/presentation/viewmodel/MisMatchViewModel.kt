package com.csci448.avelychko.mis_match.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.data.database.Picture
import kotlinx.coroutines.flow.MutableStateFlow

class MisMatchViewModel(val topList: List<Picture>, val bottomList: List<Picture>, val shoeList: List<Picture>) {
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
        indexBottom++
        selectedBottomState.value = bottomList[indexBottom]
    }
    
    fun moveToNextShoe() {
        indexShoe++
        selectedShoeState.value = shoeList[indexShoe]
    }
    
    fun moveToPrevTop() {
        indexTop--
        selectedTopState.value = topList[indexTop]
    }
    
    fun moveToPrevBottom() {
        indexBottom--
        selectedBottomState.value = bottomList[indexBottom]
    }
    
    fun moveToPrevShoe() {
        indexShoe--
        selectedShoeState.value = shoeList[indexShoe]
    }
}
