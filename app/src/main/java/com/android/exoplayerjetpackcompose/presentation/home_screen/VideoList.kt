package com.android.exoplayerjetpackcompose.presentation.home_screen

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.exoplayerjetpackcompose.R
import com.android.exoplayerjetpackcompose.domain.VideoItem

@Composable
fun VideoList(
    videoItems: List<VideoItem>, selectedVideoLauncher: ManagedActivityResultLauncher<String, Uri?>,
//    viewModel: HomeViewModel
    playVideoCallback: (Uri) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))

    Row(modifier = Modifier
        .clickable {
            selectedVideoLauncher.launch("video/mp4")
        }
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.FileOpen,
            contentDescription = stringResource(R.string.select_video_from_files)
        )
        Text(
            text = stringResource(R.string.select_a_video_to_play),
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(videoItems) { item ->
            Text(text = item.name, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    playVideoCallback(item.contentUri)
                }
                .padding(16.dp))
        }
    }
}