package com.example.androidplaylist.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(FragmentComponent::class)
class Playlismodule {

    @Provides
    fun retrofit():Retrofit=Retrofit.Builder()
        .baseUrl("http://192.168.1.29:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun playlistAPI(retrofit: Retrofit):PlaylistAPI{

        return retrofit.create(PlaylistAPI::class.java)
    }



    //hilt will automatically make a connection between the two methods
}