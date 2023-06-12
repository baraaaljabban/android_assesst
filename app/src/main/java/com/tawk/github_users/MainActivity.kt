package com.tawk.github_users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.tawk.github_users.core.navigator.NavigationComposable
import com.tawk.github_users.core.navigator.UserDetailsScreenArgsEvent
import com.tawk.github_users.core.theme.TawkTheme
import com.tawk.github_users.features.common.AppBar
import com.tawk.github_users.features.common.OfflineSnackBar
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


@AndroidEntryPoint
class MainActivity : ComponentActivity(), ImageLoaderFactory {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            var screen = ""
            navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            LaunchedEffect(backStackEntry) {
                screen = backStackEntry?.destination?.route ?: ""
            }


            TawkTheme {
                Scaffold(snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }, topBar = {
                    AppBar(screen, navController)

                }) { paddingValues ->

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavigationComposable(
                            paddingValues = paddingValues, navController = navController,
                        )

                        OfflineSnackBar()
                    }

                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    /**
     * Event handler for the user selected event.
     * Navigates to the user details screen based on the event data.
     */
    @Subscribe
    fun onItemSelected(event: UserDetailsScreenArgsEvent) {
        navController.navigate("user/${event.username}/${event.userLocalId}")
    }

    /**
     * to cache the images in the local data base
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this).memoryCache {
            MemoryCache.Builder(this).maxSizePercent(0.25).build()
        }.diskCache {
            DiskCache.Builder().directory(this.cacheDir.resolve("image_cache")).maxSizePercent(0.02)
                .build()
        }.build()
    }
}

