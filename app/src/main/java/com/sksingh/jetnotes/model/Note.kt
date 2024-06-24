package com.sksingh.jetnotes.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

import java.util.UUID

@Entity(tableName = "notes_table")
data class Note  @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title : String,

    @ColumnInfo(name = "note_description")
    val description :String,

    @ColumnInfo(name = "note_entry_date")
    val entryDate: LocalDate = LocalDate.now()
)
