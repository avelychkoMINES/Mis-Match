package edu.mines.csci448.examples.camera.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.mines.csci448.examples.camera.data.PhotographRepository

class PhotographViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private const val LOG_TAG = "448.PhotographViewModelFactory"
    }

    fun getViewModelClass() = PhotographViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d(LOG_TAG, "create() called")
        if(modelClass.isAssignableFrom(getViewModelClass())) {
            Log.d(LOG_TAG, "creating ViewModel for ${getViewModelClass()}")
            return modelClass
                .getConstructor(PhotographRepository::class.java)
                .newInstance(PhotographRepository.getInstance(context))
        }
        Log.e(LOG_TAG, "Unknown ViewModel $modelClass")
        throw IllegalArgumentException("Unknown ViewModel $modelClass")
    }
}