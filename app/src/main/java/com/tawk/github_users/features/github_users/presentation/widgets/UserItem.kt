package com.tawk.github_users.features.github_users.presentation.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tawk.github_users.core.navigator.UserDetailsScreenArgsEvent
import com.tawk.github_users.features.github_users.domain.enities.User
import org.greenrobot.eventbus.EventBus

@Composable
fun UserItem(user: User, index: Int) {
    val shouldInvertColors = (index + 1) % 4 == 0

    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
            .fillMaxWidth()
            .clickable {
                EventBus
                    .getDefault()
                    .post(
                        UserDetailsScreenArgsEvent(user.loginName, user.userLocalId)
                    )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                UserAvatar(
                    userAvatarURL = user.avatarURL,
                    shouldShowInvertColor = shouldInvertColors
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = user.loginName,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Details",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        if (user.hasNote())
            Icon(
                Icons.TwoTone.Menu,
                contentDescription = "Note_Icon",
                modifier = Modifier.padding(start = 8.dp, end = 16.dp)
            )
    }

}

@Preview
@Composable
fun UserItemPreview() {

    Row(
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
            .fillMaxWidth()
            .clickable {

            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                UserAvatar(userAvatarURL = "user.avatarURL", shouldShowInvertColor = false)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Details",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Icon(
            Icons.TwoTone.Menu,
            contentDescription = "Note_Icon",
            modifier = Modifier.padding(start = 8.dp, end = 16.dp)
        )
    }


}