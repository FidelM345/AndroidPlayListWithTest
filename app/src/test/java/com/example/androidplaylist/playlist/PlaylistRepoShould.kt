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
    private val playlistRaw= mock<List<PlaylistRaw>>()
    private val mapper:PlaylistMapper= mock()


    @Test
    fun `get play list from service`()= runBlockingTest{
        val  repository=PlaylistRepository(service,mapper)

        repository.getPlaylists()

        verify(service, times(1)).fetchPlaylist()
    }


    @Test
    fun `emit mapped play list from service`()= runBlockingTest{

        mockSuccesfulcase()

        val repository=PlaylistRepository(service,mapper)


        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }




    @Test
    fun propagateErrors()= runBlockingTest{
        val repository = mockErrorcase()

        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())

    }


    @Test
    fun `delegate the business logic to mapper`()= runBlockingTest{
        mockSuccesfulcase()

        val repository=PlaylistRepository(service,mapper)

        //when we get the first emissions
        repository.getPlaylists().first()

        verify(mapper, times(1)).invoke(playlistRaw)

    }


    private suspend fun mockSuccesfulcase() {
        whenever(service.fetchPlaylist()).thenReturn(
            flow {
                emit(Result.success(playlistRaw))
            }
        )
        whenever(mapper.invoke(playlistRaw)).thenReturn(playlists)
    }

    private suspend fun mockErrorcase(): PlaylistRepository {
        whenever(service.fetchPlaylist()).thenReturn(
            flow {
                emit(Result.failure<List<PlaylistRaw>>(exception))
            }
        )



        val repository = PlaylistRepository(service,mapper)
        return repository
    }



}