package com.sksingh.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sksingh.jetnotes.model.Note
import com.sksingh.jetnotes.util.DateConverter
import com.sksingh.jetnotes.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)

@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}


