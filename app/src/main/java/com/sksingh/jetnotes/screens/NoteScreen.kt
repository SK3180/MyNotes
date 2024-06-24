package com.sksingh.jetnotes.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sksingh.jetnotes.R
import com.sksingh.jetnotes.components.NoteButton
import com.sksingh.jetnotes.components.NoteComponent
import com.sksingh.jetnotes.model.Note

val customFontFamily = FontFamily(Font(R.font.mal))
val monaextra = FontFamily(Font(R.font.monaextra))
val mona = FontFamily(Font(R.font.mona))

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    notes:List<Note>,
    onAddNote : (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var seletedNote: Note? by remember { mutableStateOf(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(37, 37, 37))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = customFontFamily,
                fontSize = 55.sp,
                color = Color(60, 208, 120)
            )
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notification",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        Toast
                            .makeText(context, "No Notifications", Toast.LENGTH_SHORT)
                            .show()
                    }
                ,
                tint = Color.White
            )
        }

        Text(
            text = "Good Morning",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 15.dp, start = 15.dp),
            fontFamily = mona,
            fontWeight = FontWeight.ExtraBold,
        )

        Text(
            text = "What's on your\nmind?",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.padding(15.dp),
            fontFamily = monaextra,
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(25.dp)),
                colors = CardDefaults.cardColors(Color(50,50,50))
            ) {
                NoteComponent(
                    text = title,
                    label = "Title",
                    onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .clip(shape = RoundedCornerShape(20.dp)),
                    maxLine = 1
                )
                NoteComponent(
                    text = description,
                    label = "Add a note",
                    onTextChange = {
                         description = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
                        .clip(shape = RoundedCornerShape(20.dp)),
                    maxLine = 5
                )
                NoteButton(
                    text = "Save",
                    modifier = Modifier
                        .size(width = 120.dp, height = 49.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        if (title.isNotEmpty() && description.isNotEmpty()){
                            onAddNote(Note(title = title,
                                description = description))
                            title = ""
                            description = ""
                            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note ->
                val isSelected = seletedNote == note
                NoteRow(note = note, onNoteClicked = {
                    seletedNote = if (isSelected) null else note
                }, showDeleteButton = isSelected,onDeleteNote = {
                    onRemoveNote(note)
                    seletedNote = null
                })
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    showDeleteButton: Boolean,
    onNoteClicked: () -> Unit,
    onDeleteNote: () -> Unit
) {
    val mona = FontFamily(Font(R.font.mona))

    Surface(
        modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth(),
        color = Color(50, 50, 50),
        shadowElevation = 5.dp
    ) {
        Column(
            modifier = modifier
                .clickable {
                    onNoteClicked()
                }
                .padding(horizontal = 12.dp, vertical = 9.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                color = Color(60, 208, 120),
                fontSize = 17.sp,
                fontFamily = monaextra,
            )
            Text(
                text = note.description,
                fontSize = 15.sp,
                color = Color.White,
                fontFamily = mona,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.entryDate.format(org.threeten.bp.format.DateTimeFormatter.ofPattern("EEE, d MMM")),
                color = Color.Gray,
                fontSize = 13.sp
            )
            if (showDeleteButton) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete Note",
                    tint = Color.Red,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            onDeleteNote()
                        }
                )
            }
        }
    }
}
