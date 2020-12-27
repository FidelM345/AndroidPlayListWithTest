package com.example.androidplaylist.playlist

import kotlinx.coroutines.flow.Flow


 interface PlaylistAPI {
    suspend fun fetchAllPlaylists() : List<PlaylistItem> {
        TODO("Not yet implemented")
    }

}
