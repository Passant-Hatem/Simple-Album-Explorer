package com.example.simplealbumexplorer.modules.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplealbumexplorer.R
import com.example.simplealbumexplorer.modules.core.mics.Action


@Composable
fun ErrorScreen(
    errorMsg: String,//TODO LOCALIZE ERRORS TO READABLE ONES
    onRetryClicked: Action
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMsg,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            color = Color.Red,
            modifier = Modifier.padding(all = 18.dp)
        )

        Text(
            text = stringResource(R.string.retry_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { onRetryClicked() }
        )

    }
}