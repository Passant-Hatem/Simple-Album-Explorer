package com.example.simplealbumexplorer.modules.album.presentation.model

import com.example.simplealbumexplorer.modules.album.presentation.AlbumDetailsUIState
import com.example.simplealbumexplorer.modules.core.presentation.model.ActionStates

data class AlbumDetailsUIModel(
    val albumTitle: String,
    val imagesUrls: List<String>,
    val isLoading: Boolean,
    val isError: Boolean,
    val error: String?
)

fun AlbumDetailsUIState.toUIModel(): AlbumDetailsUIModel =
    AlbumDetailsUIModel(
        albumTitle = albumTitle,
        imagesUrls = filteredPhotos.map { it.imageUrl },
        isLoading = actionStates == ActionStates.LOADING,
        isError = actionStates == ActionStates.ERROR,
        error = errorMsg
    )
