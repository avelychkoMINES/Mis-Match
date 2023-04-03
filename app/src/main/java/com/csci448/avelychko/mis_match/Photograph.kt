package edu.mines.csci448.examples.camera.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "photograph")
data class Photograph(
    @ColumnInfo(name = "photofilename")
    val photographFileName: String,
    @PrimaryKey
    val id: UUID = UUID.randomUUID()
)