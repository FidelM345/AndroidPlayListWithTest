package com.example.androidplaylist.playlist

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface PlaylistAPI {

     @GET("playlist")
    suspend fun fetchAllPlaylists() : List<PlaylistItem>

}
