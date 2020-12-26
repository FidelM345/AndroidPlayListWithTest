package com.example.androidplaylist.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayListViewModel (
        private val repository: PlaylistRepository
):ViewModel(){

  /*

  //method 1 for implementing live data and kotlin flow

  //Result is a kotlin wrapper class used to handle results of an operation
    val playlist=MutableLiveData<Result<List<PlaylistItem>>>()

    init {
        viewModelScope.launch {
            repository.getPlaylists().collect {resultFromRepo ->
                playlist.value=resultFromRepo

            }
        }

    }*/

    //method 2 for implementing live data and kotlin flow. using the live data builder
   //we are using live data scope for the coroutine which is similar to the viewmodel scope.
    val playlist= liveData<Result<List<PlaylistItem>>> {
        emitSource(repository.getPlaylists().asLiveData())
    }

}
