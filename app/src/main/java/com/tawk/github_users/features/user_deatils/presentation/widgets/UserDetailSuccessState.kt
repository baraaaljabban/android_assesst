package com.tawk.github_users.features.user_deatils.presentation.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tawk.github_users.core.theme.TawkTheme
import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails

/**
 * Composable function for displaying the UI state when user details are successfully fetched.
 *
 * @param data The user details data to be displayed.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserDetailSuccessState(data: UserDetails) {


        Scaffold() {
            Column(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(8.dp)
                        .border(2.dp, Color.Black)
                ) {
                    val imagePainter: Painter = rememberAsyncImagePainter(
                        data.avatar_url ?: "",

                        )
                    Image(
                        painter = imagePainter, contentDescription = "Avatar Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(200.dp)
                            .padding(2.dp)
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.padding(1.dp))
                Divider(thickness = 1.dp, color = Color.Black)
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Followers: ${data.followers}",
                        style = MaterialTheme.typography.bodyMedium

                    )
                    Text(
                        text = "Following: ${data.following}",
                        style = MaterialTheme.typography.bodyMedium

                    )

                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .border(
                            width = 2.dp, color = Color.Black, shape = RectangleShape
                        )
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = "name: ${data.name}", style = MaterialTheme.typography.bodyMedium

                        )
                        Text(
                            text = "company:  ${data.company}",
                            style = MaterialTheme.typography.bodyMedium

                        )
                        Text(
                            text = "blog: ${data.blog}", style = MaterialTheme.typography.bodyMedium

                        )
                    }
                }
                Text(
                    text = "Notes:",
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyMedium

                )
                SimpleOutlinedTextFieldSample(note = data.note)
            }
        }

}