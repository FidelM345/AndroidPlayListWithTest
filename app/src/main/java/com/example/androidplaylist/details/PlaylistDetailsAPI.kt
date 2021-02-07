package com.example.androidplaylist.details

import com.example.androidplaylist.playlist.client
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
/*
 when you first implement the API interface in TDD it will be a class later it will be changed to an inteface
class PlaylistDetailsAPI @Inject constructor() {
    suspend fun fetchPlaylistDetails(id: String) : PlaylistDetailsModel{



    }
*/

interface PlaylistDetailsAPI{

    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String) : PlaylistDetailsModel



    //the retrofit builder will not be written here becoz dagger will automatically use the one
    //written in the playlistModule
   /* @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.43:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/


}
