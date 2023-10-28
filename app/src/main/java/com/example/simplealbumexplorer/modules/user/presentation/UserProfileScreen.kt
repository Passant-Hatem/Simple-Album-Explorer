package com.example.simplealbumexplorer.modules.user.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplealbumexplorer.R
import com.example.simplealbumexplorer.modules.core.mics.Action
import com.example.simplealbumexplorer.modules.core.mics.Consumer
import com.example.simplealbumexplorer.modules.core.presentation.components.ErrorScreen
import com.example.simplealbumexplorer.modules.core.presentation.components.LoadingScreen
import com.example.simplealbumexplorer.modules.user.domain.model.Album
import com.example.simplealbumexplorer.modules.user.domain.model.User
import com.example.simplealbumexplorer.modules.user.presentation.model.UserProfileUIModel

@Composable
fun UserProfileScreen(
    uiModel: UserProfileUIModel,
    getUserData: Action,
    onAlbumItemClicked: Consumer<String>
) = with(uiModel) {
    if (isError)
        ErrorScreen(errorMsg = error!!, onRetryClicked = getUserData)

    else if (isLoading)
        LoadingScreen()
    else
        UserProfileScreenContent(
            user = user!!,
            albums = albums,
            onItemClicked = onAlbumItemClicked
        )

}


@Composable
fun UserProfileScreenContent(
    user: User,
    albums: List<Album>,
    onItemClicked: Consumer<String>
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile_label),
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )

        UserHeader(user = user)

        Text(
            text = stringResource(R.string.album_label),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        AlbumList(albums, onItemClicked)
    }
}

@Composable
fun UserHeader(user: User) = with(user) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = name, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Text(text = address, fontSize = 18.sp)
    }
}

@Composable
fun AlbumList(
    albums: List<Album>,
    onItemClicked: Consumer<String>
) {
    LazyColumn {
        items(albums) { album ->
            Divider()
            Text(
                text = album.title,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clickable { onItemClicked(album.id.toString()) }
            )
        }
    }
}

