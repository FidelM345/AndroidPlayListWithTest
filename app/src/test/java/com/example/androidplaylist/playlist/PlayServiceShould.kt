package com.example.androidplaylist.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException

class PlayServiceShould {

   private val playlistApi:PlaylistAPI=mock()

    private  val playlists:List<PlaylistItem> =mock()
    private val exception=RuntimeException("something went wrong")
    @Test
    fun `fetch all playlists from playlist api`()= runBlockingTest{

        val service=PlaylistService(playlistApi)

        //calling first() method here will ensure that the fetchAllPlaylists() is also called by forcing an emission here
        service.fetchPlaylist().first()

        verify(playlistApi, times(1)).fetchAllPlaylists()
    }


    @Test
    fun `convert values to flow results and emits them`()= runBlockingTest{

        whenever(playlistApi.fetchAllPlaylists()).thenReturn(playlists)

        val service=PlaylistService(playlistApi)

        assertEquals(playlists,service.fetchPlaylist().first().getOrNull())


    }

    @Test
    fun `emit Error results when network fails`()= runBlockingTest{
        val service=PlaylistService(playlistApi)
        whenever(playlistApi.fetchAllPlaylists()).thenThrow(RuntimeException("Network has failed"))

        assertEquals("something went wrong",service.fetchPlaylist().first().exceptionOrNull()?.message)


    }

}