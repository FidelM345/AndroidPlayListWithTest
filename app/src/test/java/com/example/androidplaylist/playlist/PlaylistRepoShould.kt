package com.example.androidplaylist.playlist

import com.example.androidplaylist.utils.BaseunitTest
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


class PlaylistRepoShould :BaseunitTest(){

    private val service:PlaylistService=mock()
    private  val playlists= mock<List<PlaylistItem>>()
    private val exception=RuntimeException("something went wrong")


    @Test
    fun `get play list from service`()= runBlockingTest{
        val  repository=PlaylistRepository(service)

        repository.getPlaylists()

        verify(service, times(1)).fetchPlaylist()
    }


    @Test
    fun `emit play list from service`()= runBlockingTest{

        whenever(service.fetchPlaylist()).thenReturn(
                flow {
                    emit(Result.success(playlists))
                }
        )

        val repository=PlaylistRepository(service)


        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }


    @Test
    fun propagateErrors()= runBlockingTest{
        whenever(service.fetchPlaylist()).thenReturn(
                flow {
                    emit(Result.failure<List<PlaylistItem>>(exception))
                }
        )

        val repository=PlaylistRepository(service)

        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())

    }



}