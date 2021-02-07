package com.example.androidplaylist.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayListDetailsViewModel (
        private val service: PlayListDetailsService
        ): ViewModel(){

    //initialize the variable
    val playlistDetails: MutableLiveData<Result<PlaylistDetailsModel>> = MutableLiveData()

    fun getPlayListDetails(playlistId: String) {

        viewModelScope.launch {

            service.fetPlaylistDetails(playlistId).collect {
                //we collect every emission of our Kotlin flow and post it to our live data
                playlistDetails.postValue(it)
            }

        }
    }

}
