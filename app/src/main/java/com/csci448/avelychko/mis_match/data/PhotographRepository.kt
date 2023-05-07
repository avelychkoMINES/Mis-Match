package com.csci448.avelychko.mis_match.data

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.csci448.avelychko.mis_match.data.database.PhotographDao
import com.csci448.avelychko.mis_match.data.database.PhotographDatabase
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.UUID

class PhotographRepository
@OptIn(DelicateCoroutinesApi::class)
private constructor(private val photographDao: PhotographDao,
                    private val coroutineScope: CoroutineScope = GlobalScope) {
    companion object {
        private const val LOG_TAG = "448.PhotographRepository"

        @Volatile
        private var INSTANCE: PhotographRepository? = null

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
                    instance = PhotographRepository(database.photographDao)
                    INSTANCE = instance
                }
                //val DIRECTORY_NAME = "%your_folder_name%"

                val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    MediaStore.MediaColumns.RELATIVE_PATH + " like ? "
                else MediaStore.Images.Media.DATA + " like ? "

                Log.d(LOG_TAG, "cursor start")
                Log.d(LOG_TAG, "selection $selection")
                Log.d(LOG_TAG, "media ${MediaStore.Images.Media.EXTERNAL_CONTENT_URI}")

                val cursor = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                )
                if (cursor != null) {
                    Log.d(LOG_TAG, "cursor not null")
                    Log.d(LOG_TAG, "cursorTop $cursor")

                    Log.d(LOG_TAG, "cursorcount ${cursor.count}")
                    while (cursor.moveToNext()) {
                        Log.d(LOG_TAG, "cursorTop $cursor")
                        val absolutePathOfImage = cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                MediaStore.MediaColumns.DATA
                            )
                        )
                        Log.d(LOG_TAG, "absolutepath $absolutePathOfImage")

                        if (absolutePathOfImage.contains("Tops")) {
                            top.add(Photograph(absolutePathOfImage))
                        } else if (absolutePathOfImage.contains("Bottoms")) {
                            bottom.add(Photograph(absolutePathOfImage))
                        } else if (absolutePathOfImage.contains("Shoes")) {
                            shoe.add(Photograph(absolutePathOfImage))
                        }
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

    suspend fun getPhotographsForTriplet(tripletId: Int) = photographDao.getPhotographsForTriplet(tripletId)

    suspend fun getAllTriplets(): List<Triplet> {
        val allTriplets = photographDao.getAllTriplets()
        Log.d(LOG_TAG, "triplets: ${allTriplets}")
//        for (triplet in allTriplets) {
//            val photographs = photographDao.getPhotographsForTriplet(triplet.tripletId)
//            triplet.top = photographs.find { it.photographFileName.contains("top", ignoreCase = true) }
//            triplet.bottom = photographs.find { it.photographFileName.contains("bottom", ignoreCase = true) }
//            triplet.shoe = photographs.find { it.photographFileName.contains("shoe", ignoreCase = true) }
//        }

        return allTriplets
    }

    fun addTriplet(triplet: Triplet) {
        coroutineScope.launch {
            try {
                photographDao.addTriplet(triplet)
                Log.d("Repository", "Triplet added successfully")
            } catch (e: Exception) {
                Log.e("Repository", "Error adding triplet: ${e.message}")
            }
        }
    }

    fun deleteTriplet(triplet: Triplet) {
        coroutineScope.launch {
            photographDao.deleteTriplet(triplet)
        }
    }

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
