package com.tawk.github_users.core.navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tawk.github_users.features.github_users.presentation.widgets.PagingListScreen
import com.tawk.github_users.features.github_users.presentation.widgets.SearchResultScreen
import com.tawk.github_users.features.user_deatils.presentation.ui.widgets.UserDetailsScreen

@Composable
fun NavigationComposable(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .padding(paddingValues),
        navController = navController,
        startDestination = ROUTE_USERS
    ) {
        composable(route = ROUTE_USERS, content = { PagingListScreen() })
        composable(route = SEARCH_RESULT, arguments = listOf(
            navArgument(QUERY) {
                type = NavType.StringType
            }
        )) { backStackEntry ->
            val query =
                backStackEntry.arguments?.getString(QUERY).orEmpty()
            SearchResultScreen(query)
        }

        composable(
            route = ROUTE_USER,
            arguments = listOf(
                navArgument(ARG_USER_NAME) {
                    type = NavType.StringType
                },
                navArgument(ARG_USER_Local_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val username =
                backStackEntry.arguments?.getString(ARG_USER_NAME).orEmpty()
            val localId = backStackEntry.arguments?.getInt(ARG_USER_Local_ID) ?: -1
            UserDetailsScreen(
                username,
                localId,
                navController,
            )
        }

    }
}


data class UserDetailsScreenArgsEvent(
    val username: String,
    val userLocalId: Int

)

private const val ARG_USER_NAME = "name"
private const val ARG_USER_Local_ID = "localId"
private const val QUERY = "query"

const val ROUTE_USERS = "users"
const val ROUTE_USER = "user/{$ARG_USER_NAME}/{$ARG_USER_Local_ID}"
const val SEARCH_RESULT = "searchResult/{$QUERY}"
