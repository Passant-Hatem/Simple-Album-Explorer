package com.example.simplealbumexplorer.modules.album.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.simplealbumexplorer.R
import com.example.simplealbumexplorer.modules.album.presentation.model.AlbumDetailsUIModel
import com.example.simplealbumexplorer.modules.core.mics.Action
import com.example.simplealbumexplorer.modules.core.mics.Consumer
import com.example.simplealbumexplorer.modules.core.presentation.components.ErrorScreen
import com.example.simplealbumexplorer.modules.core.presentation.components.LoadingScreen


@Composable
fun AlbumDetailsScreen(
    uiModel: AlbumDetailsUIModel,
    getAlbumDetails: Action,
    onSearchClicked: Consumer<String>
) = with(uiModel) {
    if (isError)
        ErrorScreen(errorMsg = error!!, onRetryClicked = getAlbumDetails)
    else if (isLoading)
        LoadingScreen()
    else
        AlbumDetailsScreenContent(
            albumTitle = albumTitle,
            images = imagesUrls,
            onSearchClicked = onSearchClicked
        )
}


@Composable
fun AlbumDetailsScreenContent(
    albumTitle: String,
    images: List<String>,
    onSearchClicked: Consumer<String>
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            text = albumTitle,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 14.dp)
        )
        Divider(Modifier.padding(vertical = 8.dp))
        SearchSection(onSearchClicked)
        Spacer(modifier = Modifier.height(16.dp))
        PhotosGrid(images)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    updateSearchQuery: Consumer<String>,
) {
    var searchQuery by remember { mutableStateOf("") }
    var showHint by remember { mutableStateOf(true) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.LightGray)

    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                updateSearchQuery(it)
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(30.dp)
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        LaunchedEffect(searchQuery) {
            showHint = searchQuery.isEmpty()
        }
        if (showHint)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
            ) {
                Spacer(modifier = Modifier.width(36.dp))
                Text(
                    text = stringResource(id = R.string.search_fiels_hint),
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }

    }
}

@Composable
fun PhotosGrid(
    images: List<String>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(images) {
            AsyncImage(
                model = it,
                contentDescription = null
            )
        }
    }
}