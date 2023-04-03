package com.csci448.avelychko.mis_match.data.database

import androidx.room.TypeConverter
import java.util.*

class PhotographTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid.toString()

    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)
}