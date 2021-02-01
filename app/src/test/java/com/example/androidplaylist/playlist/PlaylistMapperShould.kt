package com.example.androidplaylist.playlist

import com.example.androidplaylist.R
import com.example.androidplaylist.utils.BaseunitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistMapperShould :BaseunitTest(){

    //real object has been used because we want to use the real values
    private val playlistRaw=PlaylistRaw("1","morris","pop")
    private val playlistRawRock=PlaylistRaw("1","morris","rock")

    private val mapper=PlaylistMapper()

    private val playlists=mapper(listOf(playlistRaw))
    private val playlist=playlists[0]

    private val playlistRock=mapper(listOf(playlistRawRock))[0]

    @Test
    fun `should keep playlist id`(){

        assertEquals(playlistRaw.id,playlist.id)
    }


    @Test
    fun `should keep playlist name`(){

        assertEquals(playlistRaw.name,playlist.name)
    }


    @Test
    fun `should keep playlist category`(){

        assertEquals(playlistRaw.name,playlist.name)
    }

    @Test
    fun `map default image when not rock`(){

        assertEquals(R.drawable.playlislogo,playlist.image)
    }

    @Test
    fun `map rock image when it is rock category`(){

        assertEquals(R.drawable.rock,playlistRock.image)
    }






}