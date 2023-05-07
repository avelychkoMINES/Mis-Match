package com.csci448.avelychko.mis_match.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "photograph")
data class Photograph(
    @ColumnInfo(name = "photofilename")
    val photographFileName: String,
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var tripletId: Int = 0
)

@Entity(tableName = "triplet")
data class Triplet(
    @PrimaryKey(autoGenerate = true)
    val tripletId: Int = 0,
    val topFile: String,
    val bottomFile: String,
    val shoeFile: String
)

@Entity(tableName = "photograph_triplet_cross_ref",
    primaryKeys = ["photographUuid", "tripletId"],
    foreignKeys = [
        ForeignKey(entity = Photograph::class, parentColumns = ["uuid"], childColumns = ["photographUuid"]),
        ForeignKey(entity = Triplet::class, parentColumns = ["tripletId"], childColumns = ["tripletId"])
    ]
)
data class PhotographTripletCrossRef(
    val photographUuid: String,
    val tripletId: Int
)