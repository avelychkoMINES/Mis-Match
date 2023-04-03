package edu.mines.csci448.examples.camera.data.database

import androidx.room.*
import edu.mines.csci448.examples.camera.data.Photograph
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface PhotographDao {

    @Insert
    suspend fun addPhotograph(photograph: Photograph)

    @Query("SELECT * FROM photograph")
    fun getPhotographs(): Flow<List<Photograph>>

    @Query("SELECT * FROM photograph WHERE id=(:id)")
    suspend fun getPhotograph(id: UUID): Photograph?

    @Delete
    suspend fun deletePhotograph(photograph: Photograph)

}