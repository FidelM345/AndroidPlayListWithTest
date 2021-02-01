package com.example.androidplaylist.playlist

import com.example.androidplaylist.R
import javax.inject.Inject


//Function1 in kotlin is a function that accepts a single input and also gives out a single output
class PlaylistMapper @Inject constructor():Function1<List<PlaylistRaw>, List<PlaylistItem>>{
    override fun invoke(playlistraw: List<PlaylistRaw>): List<PlaylistItem> {

        return playlistraw.map {
            val image=when(it.category){
                 "rock"->R.drawable.rock
                   else -> R.drawable.playlislogo
            }
            PlaylistItem(it.id,it.name,it.category, image)
        }

    }


}
