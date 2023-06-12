package com.tawk.github_users.features.user_deatils.presentation.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
 import com.tawk.github_users.features.common.Loader
import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails
import com.tawk.github_users.features.user_deatils.presentation.vm.FetchUserResultState
import com.tawk.github_users.features.user_deatils.presentation.vm.UserDetailsViewModel

 @Composable
fun UserDetailsScreen(username: String, userLocalId: Int) {
    val viewModel = hiltViewModel<UserDetailsViewModel>()
    LaunchedEffect(key1 = username, block = {
        viewModel.fetchUserDetails(username = username, userLocalId)

    })

    val fetchUserResultState = viewModel.fetchUserResultState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {

        when (val resultState = fetchUserResultState.value) {
            is FetchUserResultState.Loading -> {
                Loader()
            }

            is FetchUserResultState.Error -> {
                val error = resultState.message
                Text(text = error)
            }

            is FetchUserResultState.Success -> {
                val data = resultState.data
                UserDetailSuccessState(data = data)
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun Preview() {
    val pageTypography = Typography(
        body1 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(Modifier.fillMaxSize()) {
            TopAppBar(backgroundColor = Color.White, title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(end = 48.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),

                        maxLines = 1,
                        style = pageTypography.body1,
                        text = "Name",
                        textAlign = TextAlign.Center
                    )
                }
            }, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                    )
                }
            })

            val data = UserDetails(
                "",
                "",
                "",
                "",
                3,
                3,
                "1",
                "3",

                )
            UserDetailSuccessState(data = data)
        }
    }

}

