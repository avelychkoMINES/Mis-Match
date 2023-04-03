package com.csci448.avelychko.mis_match.data.database

import androidx.room.*
import com.csci448.avelychko.mis_match.data.Photograph
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