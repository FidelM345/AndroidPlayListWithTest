package com.example.androidplaylist.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val service: PlaylistService, private val mapper: PlaylistMapper) {

    suspend fun getPlaylists(): Flow<Result<List<PlaylistItem>>>{

        //1 st code when testing `get play list from service`
        //service.fetchPlaylist()
        /*return flow {

        }*/


        //2nd code when testing `emit play list from service`
        return service.fetchPlaylist().map {

            if (it.isSuccess)
            //here the PlaylistRaw is transformed to PlaylistItem by the mapper
            Result.success(mapper(it.getOrNull()!!))

            else

                Result.failure(it.exceptionOrNull()!!)
        }

    }


}
