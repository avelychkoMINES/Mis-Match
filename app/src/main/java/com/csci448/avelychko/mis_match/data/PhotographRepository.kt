package com.csci448.avelychko.mis_match.data

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.LogPrinter
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

        val top: MutableList<Photograph> = mutableListOf()
        //top = topList.toList()

        val bottom: MutableList<Photograph> = mutableListOf()
        //bottom = bottomList.toList()

        val shoe: MutableList<Photograph> = mutableListOf()
        //shoe = shoeList.toList()

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
                //val DIRECTORY_NAME = "%your_folder_name%"

                val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    MediaStore.MediaColumns.RELATIVE_PATH + " like ? "
                else MediaStore.Images.Media.DATA + " like ? "

                val selectionArgsTop = arrayOf("Picture/CameraX-Image/Top/")
                val selectionArgsBottom = arrayOf("Picture/CameraX-Image/Bottom/")
                val selectionArgsShoe = arrayOf("Picture/CameraX-Image/Shoe/")

                Log.d(LOG_TAG, "cursor start")
                Log.d(LOG_TAG, "selection $selection")
                Log.d(LOG_TAG, "media ${MediaStore.Images.Media.EXTERNAL_CONTENT_URI}")

                val cursorTop = context.contentResolver.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null,
                        //selection,
                        //selectionArgsTop,
                    null,
                    selectionArgsTop,
                        null)
                if (cursorTop != null) {
                    Log.d(LOG_TAG, "cursor not null")
                    Log.d(LOG_TAG, "cursorTop $cursorTop")

                    Log.d(LOG_TAG, "cursorcount ${cursorTop.count}")
                    while(cursorTop.moveToNext()) {
                        Log.d(LOG_TAG, "cursorTop $cursorTop")
                        val absolutePathOfImage = cursorTop.getString(cursorTop.getColumnIndexOrThrow(
                            MediaStore.MediaColumns.DATA))
                        Log.d(LOG_TAG, "absolutepath $absolutePathOfImage")

                        if (absolutePathOfImage.contains("top")) {
                            top.add(Photograph(absolutePathOfImage))
                        }
                    }
                }

                val cursorBottom = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null,
                    selection,
                    selectionArgsBottom,
                    null)
                if (cursorBottom != null) {
                    while(cursorBottom.moveToNext()) {
                        val absolutePathOfImage = cursorBottom.getString(cursorBottom.getColumnIndexOrThrow(
                            MediaStore.MediaColumns.DATA))
                        bottom.add(Photograph(absolutePathOfImage))
                    }
                }

                val cursorShoe = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null,
                    selection,
                    selectionArgsShoe,
                    null)
                if (cursorShoe != null) {
                    while(cursorShoe.moveToNext()) {
                        val absolutePathOfImage = cursorShoe.getString(cursorShoe.getColumnIndexOrThrow(
                            MediaStore.MediaColumns.DATA))
                        shoe.add(Photograph(absolutePathOfImage))
                    }
                }
                return instance
            }
        }
    }



    init {
//        val topList = mutableListOf<Photograph>()
//        top = topList.toList()
//
//        val bottomList = mutableListOf<Photograph>()
//        bottom = bottomList.toList()
//
//        val shoeList = mutableListOf<Photograph>()
//        shoe = shoeList.toList()
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

    fun getTopPhoto(): MutableList<Photograph> {
        return top
    }

    fun getBottomPhoto(): List<Photograph> {
        return bottom
    }

    fun getShoePhoto(): List<Photograph> {
        return shoe
    }
}
