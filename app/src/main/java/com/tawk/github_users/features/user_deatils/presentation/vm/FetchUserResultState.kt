package com.tawk.github_users.features.user_deatils.presentation.vm

import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails

sealed class FetchUserResultState {
    class Success(val data: UserDetails) : FetchUserResultState()
    class Error(val message: String) : FetchUserResultState()
    object Loading : FetchUserResultState()

}