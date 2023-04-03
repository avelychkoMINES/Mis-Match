package edu.mines.csci448.examples.camera.data.database

import androidx.room.TypeConverter
import java.util.*

class PhotographTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid.toString()

    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)
}