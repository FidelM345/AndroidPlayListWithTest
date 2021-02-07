package com.example.androidplaylist.details

import com.example.androidplaylist.utils.BaseunitTest
import com.nhaarman.mockitokotlin2.mock

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException


class PlaylistDetailServiceShould :BaseunitTest(){

    lateinit var service:PlayListDetailsService
    private val id="100"

    private val api: PlaylistDetailsAPI = mock()

    private val playlistDetails: PlaylistDetailsModel= mock()

    private val exception=RuntimeException(" Damn backednd developers again 500")

    @Test
    fun `fetch playlist details from api`()= runBlockingTest{

        service= PlayListDetailsService(api)

        //the single method asks our Kotlinflow to emit the first result
        service.fetPlaylistDetails(id).single()

        verify(api, times(1)).fetchPlaylistDetails(id)
    }


    @Test
    fun `convert values to flow results and emit them`()= runBlockingTest{

         whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)

         service=PlayListDetailsService(api)

        //the single method is used to retrieve the first emission of the fetchPlaylistDetails method
        //we wrap the playlistDetails in a Kotlin Result class because the results from the fetchPlaylistDetails() is also
        //wrapped in the Result class.
        assertEquals(Result.success(playlistDetails), service.fetPlaylistDetails(id).single())

    }


    @Test
    fun `emit or return error Results when the Network fails`()= runBlockingTest{

        //mocking the api when failure occurs. It throws an error when an exception occurs
        whenever(api.fetchPlaylistDetails(id)).thenThrow(exception)

        service=PlayListDetailsService(api)

        //the exceptionorNull() returns failure or exception if there is an error and null if it is a success
        assertEquals("Something went wrong", service.fetPlaylistDetails(id).single().exceptionOrNull()?.message)

    }






}