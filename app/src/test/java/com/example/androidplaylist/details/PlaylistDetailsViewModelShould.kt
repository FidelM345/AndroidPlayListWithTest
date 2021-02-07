package com.example.androidplaylist.details

import androidx.lifecycle.ViewModel
import com.example.androidplaylist.utils.BaseunitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException

class PlaylistDetailsViewModelShould: BaseunitTest() {

    lateinit var viewModel:PlayListDetailsViewModel
    private val id="1"
    private val service:PlayListDetailsService=mock()
    private val playlistDetailsModel:PlaylistDetailsModel= mock()

    private val expected=Result.success(playlistDetailsModel)
    private val exception=RuntimeException("Something went wrong")

    //wrapping the exception in kotlin Result class
    private val error=Result.failure<PlaylistDetailsModel>(exception)


    @Test
    fun `get playlist details from service`()= runBlockingTest{

        mockSuccessfulCase()

        //used to force an emission from the playlistDetails live data
        viewModel.playlistDetails.getValueForTest()

        verify(service, times(1)).fetPlaylistDetails(id)

    }



    @Test
    fun `emit Playlist details from service`()= runBlockingTest{

        mockSuccessfulCase()

        assertEquals(expected, viewModel.playlistDetails.getValueForTest())


    }

    @Test
    fun  `emit error when service fails`()= runBlockingTest{

        mockErrorCase()

        assertEquals(error,viewModel.playlistDetails.getValueForTest())

    }

    private suspend fun mockErrorCase() {
        whenever(service.fetPlaylistDetails(id)).thenReturn(
                flow {
                    emit(error)
                }
        )


        //creating instance of the viewModel
        viewModel = PlayListDetailsViewModel(service)

        viewModel.getPlayListDetails(id)
    }


    private suspend fun mockSuccessfulCase() {

        //we must mock the fetPlaylistDetails because the service object is also mocked
        whenever(service.fetPlaylistDetails(id)).thenReturn(
                flow {
                    emit(expected)
                }
        )

        //creating instance of the view model
        viewModel= PlayListDetailsViewModel(service)

        //calling this methods also indirectly calls the fetPlaylistDetails method
        viewModel.getPlayListDetails(id)
    }



}