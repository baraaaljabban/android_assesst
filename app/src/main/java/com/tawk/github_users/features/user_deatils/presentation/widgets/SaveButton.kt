package com.tawk.github_users.features.user_deatils.presentation.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tawk.github_users.features.user_deatils.presentation.vm.UserDetailsViewModel

@Composable
fun SimpleOutlinedTextFieldSample(note: String) {
    var text by rememberSaveable { mutableStateOf(note) }
     val viewmodel = hiltViewModel<UserDetailsViewModel>()
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        maxLines = 9,
        minLines = 7,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .border(2.dp, Color.Black),
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), contentAlignment = Alignment.Center

    ) {
        Box {
            Button(
                onClick = { viewmodel.saveUpdateUserNote(note = text) },
                modifier = Modifier.border(2.dp, Color.Black, RectangleShape),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),


                ) {
                Text("Save", style = TextStyle(color = Color.Black))
            }
        }
    }
}