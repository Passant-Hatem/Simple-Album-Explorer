package com.example.simplealbumexplorer.modules.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simplealbumexplorer.modules.user.presentation.model.toUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = viewModel.state.value
                UserProfileScreen(
                    uiModel = state.toUIModel(),
                    getUserData = viewModel::getUserData,
                    onAlbumItemClicked = ::navigateToAlbumDetails
                )
            }
        }
    }


    private fun navigateToAlbumDetails(albumId: String){
        val action = UserProfileFragmentDirections.actionUserProfileFragmentToAlbumDetailsFragment(albumId)
        findNavController().navigate(action)
    }

}