package com.example.locationalert.data


import android.graphics.Bitmap

data class MusicListModel(
    val title: String?,
    val artist: String?,
    val album: String?,
    val duration: String?,
    val thumbnail: Bitmap?,
    val filePath:String,
    var isSelected: Boolean = false

)