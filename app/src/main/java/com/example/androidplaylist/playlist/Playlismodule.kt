package com.example.androidplaylist.playlist

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val client=OkHttpClient()
val resource = OkHttp3IdlingResource.create("OkHttp", client);


@Module
@InstallIn(FragmentComponent::class)
class Playlismodule {

    @Provides
    fun retrofit():Retrofit=Retrofit.Builder()
        .baseUrl("http://192.168.1.28:3000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun playlistAPI(retrofit: Retrofit):PlaylistAPI{

        return retrofit.create(PlaylistAPI::class.java)
    }



    //hilt will automatically make a connection between the two methods
}