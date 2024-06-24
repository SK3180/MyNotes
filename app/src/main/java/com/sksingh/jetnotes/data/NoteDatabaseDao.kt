package com.sksingh.jetnotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sksingh.jetnotes.model.Note
import kotlinx.coroutines.flow.Flow



@Dao
interface NoteDatabaseDao {


    @Query("SELECT * from notes_table")
    fun getNotes():Flow<List<Note>>

    @Query("SELECT * From notes_table where id = :id")
  suspend fun getNoteById(id:String):Note


    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(note: Note)


    @Update(onConflict = OnConflictStrategy.REPLACE)
 suspend fun update(note: Note)

    @Query("DELETE from notes_table")
  suspend fun deleteAll()

    @Delete
    suspend fun  deleteNote(note: Note)

}
