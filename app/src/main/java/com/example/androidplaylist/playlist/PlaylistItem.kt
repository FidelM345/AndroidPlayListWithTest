package com.example.androidplaylist.playlist

import com.example.androidplaylist.R

data class PlaylistItem (
        val id:String,
        val title:String,
        val category:String,
        val image:Int= R.drawable.playlislogo
)