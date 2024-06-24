package com.sksingh.jetnotes

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sksingh.jetnotes.model.NoteViewModel
import com.sksingh.jetnotes.screens.NoteScreen
import com.sksingh.jetnotes.ui.theme.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContent {
            JetNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val notesViewModel = viewModel<NoteViewModel>()
                    NotesApp(notesViewModel)

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesApp(notesViewModel: NoteViewModel){
    val notesList = notesViewModel.noteList.collectAsState().value

    NoteScreen(notes = notesList,
        onAddNote = { notesViewModel.addNote(it) },
        onRemoveNote = { notesViewModel.removeNote(it) })
}
