package com.android.exoplayerjetpackcompose.presentation.home_screen

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.android.exoplayerjetpackcompose.domain.VideoItem
import com.android.exoplayerjetpackcompose.util.Constants
import com.android.exoplayerjetpackcompose.util.MetaDataReader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    val metaDataReader: MetaDataReader
) : ViewModel() {

    private val videoURIs = savedStateHandle.getStateFlow(Constants.VIDEO_URIS, emptyList<Uri>())

    val videoItems = videoURIs.map { uris ->
        uris.map {
            VideoItem(
                contentUri = it,
                name = metaDataReader.getMetaDataFromUri(it)?.fileName ?: "No Name",
                mediaItem = MediaItem.fromUri(it)
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        player.prepare()
    }

    fun addVideoURI(uri: Uri) {
        savedStateHandle[Constants.VIDEO_URIS] = videoURIs.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri: Uri) {
        player.setMediaItem(
            videoItems.value.find {
                it.contentUri == uri
            }?.mediaItem ?: return
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}