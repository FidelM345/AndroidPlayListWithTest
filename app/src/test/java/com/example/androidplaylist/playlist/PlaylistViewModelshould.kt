package com.example.androidplaylist.playlist

import com.example.androidplaylist.playlist.PlayListViewModel
import com.example.androidplaylist.playlist.PlaylistItem
import com.example.androidplaylist.playlist.PlaylistRepository
import com.example.androidplaylist.utils.BaseunitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException


class PlaylistViewModelshould: BaseunitTest() {

    private val repository: PlaylistRepository = mock()//mocking the repo class
    private val playlist= mock<List<PlaylistItem>>()
    private val expected=Result.success(playlist)//used to mock a successful result of playlist. So we use the successful results builder.
    private val exception=RuntimeException("Something went wrong")



    @Test
    fun `get play list from Repo`()= runBlockingTest {

        mockSuccesfulcase()


        //view model is the class under test hence its the only object that should be real. All the other objects must be mocked.
       val viewModel= PlayListViewModel(repository)


        //the getValueTest comes from InstantTaskExecutorRule
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }



    @Test
    fun `emit playlist from the repo`()= runBlockingTest{
        mockSuccesfulcase()


        //view model is the class under test hence its the only object that should be real. All the other objects must be mocked.
        val viewModel= PlayListViewModel(repository)

        assertEquals(expected, viewModel.playlist.getValueForTest())

    }


    @Test
    fun `emit error when it receives an error`()= runBlockingTest{

        whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<PlaylistItem>>(exception))
                }
        )



        val viewModel= PlayListViewModel(repository)

        assertEquals(exception, viewModel.playlist.getValueForTest()!!.exceptionOrNull())



    }



    private fun TestCoroutineScope.mockSuccesfulcase() {
        runBlockingTest {
            //mocking the getPlaylists() method from the repo
            whenever(repository.getPlaylists()).thenReturn(
                    flow {
                        emit(expected)
                    }
            )
        }
    }
}