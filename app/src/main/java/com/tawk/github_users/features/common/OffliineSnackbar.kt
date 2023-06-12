package com.tawk.github_users.features.common

import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tawk.github_users.core.connectivity.ConnectivityViewModel

@Composable
fun OfflineSnackBar() {
    val viewModel = hiltViewModel<ConnectivityViewModel>()
    val connectivityState by viewModel.connectivityState.collectAsState()

    if (!connectivityState.isConnected) {
        Box (){
            Snackbar(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                content = { Text(text = "You are offline!") },
                action = {
                    TextButton(onClick = { /* Handle action here */ }) {
                        Text(text = "Dismiss")
                    }
                }
            )
        }
    }
}
