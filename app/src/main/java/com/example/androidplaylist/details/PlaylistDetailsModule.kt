package com.example.androidplaylist.details

import com.example.androidplaylist.playlist.PlaylistAPI
import com.example.androidplaylist.playlist.client
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//the module tells dagger how to create an Instance of the API which is an interface
@Module
@InstallIn(FragmentComponent::class)
class PlaylistDetailsModule {
    //used for creating an instance of retrofit

    @Provides
    fun playlistDetailsAPI(retrofit: Retrofit): PlaylistDetailsAPI {

        return retrofit.create(PlaylistDetailsAPI::class.java)
    }
}




