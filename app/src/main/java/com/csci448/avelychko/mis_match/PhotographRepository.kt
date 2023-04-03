package edu.mines.csci448.examples.camera.data

import android.content.Context
import android.util.Log
import com.csci448.avelychko.mis_match.data.Photograph
import edu.mines.csci448.examples.camera.data.database.PhotographDao
import edu.mines.csci448.examples.camera.data.database.PhotographDatabase
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
                return instance
            }
        }
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