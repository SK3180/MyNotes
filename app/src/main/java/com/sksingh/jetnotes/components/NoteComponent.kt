package com.sksingh.jetnotes.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sksingh.jetnotes.screens.mona


@Composable
fun NoteComponent(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(51,51,51),
            unfocusedContainerColor = Color(50, 50, 50),
            disabledContainerColor = Color.White,
            focusedTextColor = Color(60, 208, 120),
            unfocusedTextColor = Color.Gray
        ),
        maxLines = maxLine,
        label = {
            Text(
                text = label,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = mona,
                fontWeight = FontWeight.Bold
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color(60, 208, 120))
    ) {
        Text(
            text,
            color = Color.Black,
            fontSize = 25.sp,
            fontFamily = mona,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteComponent() {
    NoteComponent(
        text = "Sample Text",
        label = "Label",
        onTextChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteButton() {
    NoteButton(
        text = "Save",
        onClick = {}
    )
}
