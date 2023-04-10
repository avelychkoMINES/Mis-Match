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
        if (indexTop >= topList.length()) {
            indexTop = 0
        }
        selectedTopState.value = topList[indexTop]
    }
    
    fun moveToNextBottom() {
        indexBottom++
        if (indexBottom >= bottomList.length()) {
            indexBottom = 0
        }
        selectedBottomState.value = bottomList[indexBottom]
    }
    
    fun moveToNextShoe() {
        indexShoe++
        if (indexShoe >= shoeList.length()) {
            indexShoe = 0
        }
        selectedShoeState.value = shoeList[indexShoe]
    }
    
    fun moveToPrevTop() {
        indexTop--
        if (indexTop < 0) {
            indexTop = topList.length - 1
        }
        selectedTopState.value = topList[indexTop]
    }
    
    fun moveToPrevBottom() {
        indexBottom--
        if (indexBottom < 0) {
            indexBottom = bottomList.length - 1
        }
        selectedBottomState.value = bottomList[indexBottom]
    }
    
    fun moveToPrevShoe() {
        if (indexShoe < 0) {
            indexShoe = shoeList.length - 1
        }
        indexShoe--
        selectedShoeState.value = shoeList[indexShoe]
    }
}
