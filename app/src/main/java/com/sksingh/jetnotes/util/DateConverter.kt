package com.sksingh.jetnotes.util

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId


class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: LocalDate):Long{
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    @TypeConverter
    fun dateFromTime(timestamp:Long): LocalDate{
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()

    }
}