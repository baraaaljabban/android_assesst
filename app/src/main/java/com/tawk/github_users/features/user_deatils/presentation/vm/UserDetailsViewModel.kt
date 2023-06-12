package com.tawk.github_users.features.user_deatils.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawk.github_users.features.user_deatils.domain.use_case.FetchUserDetails
import com.tawk.github_users.features.user_deatils.domain.use_case.SaveNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val fetchUserDetailsUseCase: FetchUserDetails, private val saveNoteUseCase: SaveNote
) : ViewModel() {


    private val _fetchUserResultState =
        MutableStateFlow<FetchUserResultState>(FetchUserResultState.Loading)
    val fetchUserResultState: StateFlow<FetchUserResultState> = _fetchUserResultState


    private var fetchJob: Job? = null
    private var userLocalId: Int = -1
    fun fetchUserDetails(username: String, userLocalId: Int) {
        this.userLocalId = userLocalId;
        if (fetchJob?.isActive == true) {
            return
        }
        _fetchUserResultState.value = FetchUserResultState.Loading
        viewModelScope.launch {
            runCatching {
                fetchUserDetailsUseCase.fetchUserDetails(username = username, userLocalId)
            }.onFailure {
                _fetchUserResultState.value =
                    FetchUserResultState.Error(message = it.message ?: "Error")

            }.onSuccess {
                _fetchUserResultState.value = FetchUserResultState.Success(data = it)
            }
        }

    }

    /**
     * Saves or updates the note for the user with the current [userLocalId].
     *
     * @param note The note to be saved or updated.
     */
    fun saveUpdateUserNote(note: String) {
        if (userLocalId > -1 && !note.isNullOrEmpty()) {
            viewModelScope.launch {
                saveNoteUseCase.saveNote(note, userLocalId)
            }
        }
    }

    override fun onCleared() {
        fetchJob?.cancel() // Cancel the fetch job when the ViewModel is cleared
        super.onCleared()
    }


}

