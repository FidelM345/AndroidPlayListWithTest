package com.example.androidplaylist.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlaylistService (private val playlistAPI:PlaylistAPI){
   suspend  fun fetchPlaylist():Flow<Result<List<PlaylistItem>>> {


       Log.i("mato", "fetchPlaylist: ${playlistAPI.fetchAllPlaylists().size} ")


       return flow {
           emit(Result.success(playlistAPI.fetchAllPlaylists()))
       }.catch {
           emit(Result.failure(RuntimeException("something went wrong")))
       }
    }



}
