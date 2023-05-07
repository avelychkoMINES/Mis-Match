package com.csci448.avelychko.mis_match.data.database

import androidx.room.*
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.data.Triplet
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface PhotographDao {

    @Insert
    suspend fun addPhotograph(photograph: Photograph)

    @Query("SELECT * FROM photograph")
    fun getPhotographs(): Flow<List<Photograph>>

//    @Query("SELECT * FROM photographs WHERE tripletId = :tripletId")
//    suspend fun getPhotographsForTriplet(tripletId: Int): Flow<List<Photograph>>

    @Query("SELECT * FROM photograph WHERE id=(:id)")
    suspend fun getPhotograph(id: UUID): Photograph?

    @Delete
    suspend fun deletePhotograph(photograph: Photograph)


    @Query("SELECT * FROM triplet")
    suspend fun getAllTriplets(): List<Triplet>

    @Transaction
    @Query("SELECT * FROM photograph WHERE tripletId = :tripletId")
    fun getPhotographsForTriplet(tripletId: Int): List<Photograph>

    @Insert
    suspend fun addTriplet(triplet: Triplet)

    @Delete
    suspend fun deleteTriplet(triplet: Triplet)

}