package com.example.androidplaylist.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidplaylist.playlist.PlayListViewModel
import com.example.androidplaylist.playlist.PlaylistRepository
import javax.inject.Inject

class PlayListDetailsViewModelFactory @Inject constructor(
private val  service: PlayListDetailsService
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayListDetailsViewModel(service) as T
    }

}
