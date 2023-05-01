package com.csci448.avelychko.mis_match.data

import android.content.Context
import android.util.Log
import com.csci448.avelychko.mis_match.data.database.PhotographDao
import com.csci448.avelychko.mis_match.data.database.PhotographDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class PhotographRepository
@OptIn(DelicateCoroutinesApi::class)
private constructor(private val photographDao: PhotographDao,
                    private val coroutineScope: CoroutineScope = GlobalScope) {
    companion object {
        private const val LOG_TAG = "448.PhotographRepository"

        @Volatile private var INSTANCE: PhotographRepository? = null

        fun getInstance(context: Context): PhotographRepository {
            synchronized(this) {
                Log.d(LOG_TAG, "getInstance(Context) called")
                var instance = INSTANCE
                if (instance == null) {
                    Log.d(LOG_TAG, "creating PhotographRepository instance")
                    val database = PhotographDatabase.getInstance(context)
                    instance = PhotographRepository( database.photographDao )
                    INSTANCE = instance
                }
                val DIRECTORY_NAME = "%your_folder_name%"

                val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    MediaStore.MediaColumns.RELATIVE_PATH + " like ? "
                else MediaStore.Images.Media.DATA + " like ? "

                val selectionArgs = arrayOf(APP_RESOURCE_DIRECTORY_NAME)

                val cursor = context.contentResolver.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null,
                        selection,
                        selectionArgs,
                        null)
                return instance
            }
        }
    }

    val top: List<Photograph>
    val bottom: List<Photograph>
    val shoe: List<Photograph>

    init {
        val topList = mutableListOf<Photograph>()
        top = topList.toList()

        val bottomList = mutableListOf<Photograph>()
        bottom = bottomList.toList()

        val shoeList = mutableListOf<Photograph>()
        shoe = shoeList.toList()
    }

    fun getPhotographs() = photographDao.getPhotographs()

    suspend fun getPhotograph(id: UUID) = photographDao.getPhotograph(id)

    fun addPhotograph(photograph: Photograph) {
        coroutineScope.launch {
            photographDao.addPhotograph(photograph)
        }
    }

    fun deletePhotograph(photograph: Photograph) {
        coroutineScope.launch {
            photographDao.deletePhotograph(photograph)
        }
    }
}
