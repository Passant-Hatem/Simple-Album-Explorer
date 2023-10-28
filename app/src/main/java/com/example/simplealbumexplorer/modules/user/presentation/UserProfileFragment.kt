package com.example.simplealbumexplorer.modules.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Text(
                    text = "User Profile Fragment",
                    modifier = Modifier.clickable { navigateToAlbumDetails() }
                )
            }
        }
    }


    private fun navigateToAlbumDetails(){
        val action = UserProfileFragmentDirections.actionUserProfileFragmentToAlbumDetailsFragment()
        findNavController().navigate(action)
    }

}