package com.example.simplealbumexplorer.modules.album.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simplealbumexplorer.modules.album.presentation.model.toUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {
    private val viewModel: AlbumViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = viewModel.state.value
                AlbumDetailsScreen(
                    uiModel = state.toUIModel(),
                    getAlbumDetails = viewModel::getAlbumDetails,
                    onSearchClicked = viewModel::updateSearchQuery
                )
            }
        }
    }
}