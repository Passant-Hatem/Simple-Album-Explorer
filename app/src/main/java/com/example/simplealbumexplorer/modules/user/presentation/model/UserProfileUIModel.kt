package com.example.simplealbumexplorer.modules.user.presentation.model

import com.example.simplealbumexplorer.modules.core.presentation.model.ActionStates
import com.example.simplealbumexplorer.modules.user.domain.model.Album
import com.example.simplealbumexplorer.modules.user.domain.model.User
import com.example.simplealbumexplorer.modules.user.presentation.UserState

data class UserProfileUIModel(
    val user: User?,
    val albums: List<Album>,
    val isLoading: Boolean,
    val isError: Boolean,
    val error: String?
)

fun UserState.toUIModel(): UserProfileUIModel =
    UserProfileUIModel(
        user = user,
        albums = userAlbums,
        isLoading = actionStates == ActionStates.LOADING,
        isError = actionStates == ActionStates.ERROR,
        error = errorMsg
    )