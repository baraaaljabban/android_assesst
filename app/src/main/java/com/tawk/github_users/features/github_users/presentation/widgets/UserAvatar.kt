package com.tawk.github_users.features.github_users.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

/**
 * Composable function for displaying a user's avatar image.
 *
 * @param userAvatarURL The URL of the user's avatar image to load.
 * @param shouldShowInvertColor Whether to invert the colors of the avatar image.
 * @param isRounded Whether the avatar image should be displayed with rounded corners.
 * @param height The height of the avatar image.
 * @param width The width of the avatar image.
 */
@Composable
fun UserAvatar(
    userAvatarURL: String,
    shouldShowInvertColor: Boolean = false,
    isRounded: Boolean = true,
    height: Int = 70,
    width: Int = 70,

) {
    Box(
        modifier = Modifier.padding(8.dp)

    ) {
        val colorMatrix = floatArrayOf(
            -1f, 0f, 0f, 0f, 255f,
            0f, -1f, 0f, 0f, 255f,
            0f, 0f, -1f, 0f, 255f,
            0f, 0f, 0f, 1f, 0f
        )

        SubcomposeAsyncImage(
            model = userAvatarURL,
            contentDescription = "Avatar Image",
            loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            modifier = Modifier
                .height(height = height.dp)
                .width(width = width.dp)
                .clip(if (isRounded) CircleShape else RectangleShape),
            colorFilter = if (shouldShowInvertColor) ColorFilter.colorMatrix(
                ColorMatrix(
                    colorMatrix
                )
            ) else null
        )
    }
}