package com.android.exoplayerjetpackcompose.util

import android.net.Uri

data class MetaData(
    val fileName: String
)

interface MetaDataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetaData?
}