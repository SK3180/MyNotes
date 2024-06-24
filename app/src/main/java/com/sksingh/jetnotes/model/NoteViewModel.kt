package com.sksingh.jetnotes.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sksingh.jetnotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
   //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
         repository.getAllNotes().distinctUntilChanged()
             .collect{lisOfNotes ->
                 if(lisOfNotes.isEmpty()){
                     Log.d("Empty",": Empty List")

                 }else{
                     _noteList.value = lisOfNotes

                 }

             }

        }


    }

    fun addNote(note:Note) = viewModelScope.launch { repository.addNote(note)}

    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note)}

    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }




}

