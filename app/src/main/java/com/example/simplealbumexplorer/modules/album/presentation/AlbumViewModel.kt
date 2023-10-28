package com.example.simplealbumexplorer.modules.album.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplealbumexplorer.modules.album.domain.GetAlbumDetails
import com.example.simplealbumexplorer.modules.album.domain.model.Photo
import com.example.simplealbumexplorer.modules.core.presentation.model.ActionStates
import com.example.simplealbumexplorer.modules.user.presentation.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AlbumViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val albumDetails: GetAlbumDetails
) : ViewModel() {

    private val _state = mutableStateOf(AlbumDetailsUIState())
    val state: State<AlbumDetailsUIState> = _state

    init {
        val id = savedStateHandle.get<String>("album_id") ?: ""
        val title = savedStateHandle.get<String>("album_title") ?: ""
        _state.value = state.value.copy(albumTitle = title, albumId = id)

        getAlbumDetails()

    }

    fun getAlbumDetails() {
        _state.value = state.value.copy(actionStates = ActionStates.LOADING)

        albumDetails(state.value.albumId).onEach {
            Log.d(TAG, it.toString())
            _state.value = state.value.copy(
                actionStates = ActionStates.SUCCESS,
                photos = it,
                filteredPhotos = it
            )
        }.catch {
            onFailure(it)
        }.launchIn(viewModelScope)
    }

    fun updateSearchQuery(value: String) {
        Log.d(TAG, value)
        if (value.isEmpty())
            _state.value = state.value.copy(
                filteredPhotos = state.value.photos
            )
        else
            _state.value = state.value.copy(
                filteredPhotos = state.value.photos.filter { it.title.contains(value) }
            )
    }


    private fun onFailure(error: Throwable) {
        Log.d(UserViewModel.TAG, error.toString())
        _state.value = state.value.copy(
            actionStates = ActionStates.ERROR,
            errorMsg = error.toString()
        )
    }

    companion object {
        const val TAG = "AlbumViewModel"
    }
}

data class AlbumDetailsUIState(
    var albumTitle: String = "",
    var albumId: String = "",
    var photos: List<Photo> = emptyList(),
    var filteredPhotos: List<Photo> = emptyList(),
    val actionStates: ActionStates = ActionStates.LOADING,
    val errorMsg: String? = null
)