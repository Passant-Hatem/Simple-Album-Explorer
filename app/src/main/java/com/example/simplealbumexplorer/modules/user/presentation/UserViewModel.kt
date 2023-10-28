package com.example.simplealbumexplorer.modules.user.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplealbumexplorer.modules.core.presentation.model.ActionStates
import com.example.simplealbumexplorer.modules.user.domain.GetAllUsers
import com.example.simplealbumexplorer.modules.user.domain.GetUserAlbums
import com.example.simplealbumexplorer.modules.user.domain.model.Album
import com.example.simplealbumexplorer.modules.user.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    val getAllUsers: GetAllUsers,
    val getUserAlbums: GetUserAlbums
) : ViewModel() {

    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    init {
        getUserData()
    }

    @OptIn(FlowPreview::class)
    fun getUserData() {
        _state.value = state.value.copy(actionStates = ActionStates.LOADING)

        getAllUsers().onEach {
            Log.d(TAG, it.toString())
            _state.value = state.value.copy(
                user = it.random()
            )
        }.flatMapConcat {
            getUserAlbums(state.value.user?.id.toString()).onEach { albums ->
                Log.d("$TAG Albums", albums.toString())
                _state.value = state.value.copy(
                    actionStates = ActionStates.SUCCESS,
                    userAlbums = albums
                )
            }
        }.catch {
            onFailure(it)
        }.launchIn(viewModelScope)
    }

    private fun onFailure(error: Throwable){
        Log.d(TAG, error.toString())
        _state.value = state.value.copy(
            actionStates = ActionStates.ERROR,
            errorMsg = error.toString()
        )
    }
    companion object {
        const val TAG = "UserViewModel"
    }

}

data class UserState(
    var user: User? = null,
    val userAlbums: List<Album> = emptyList(),
    val actionStates: ActionStates = ActionStates.LOADING,
    val errorMsg: String? = null
)