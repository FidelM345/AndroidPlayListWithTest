package com.example.androidplaylist.details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlayListDetailsService @Inject constructor(
  private val api: PlaylistDetailsAPI
){

    suspend fun fetPlaylistDetails(id: String): Flow<Result<PlaylistDetailsModel>> {

        return flow {

             // api.fetchPlaylistDetails(id)
            //emit(Result.success(PlaylistDetailsModel("","","")))
            emit(Result.success( api.fetchPlaylistDetails(id)))

        }.catch {
            //the catch block is used to catch any error that may occur from the backend and prevent
            //the application from crashing.

            //whenever we receive an exception we are going to emit a failure result
            emit(Result.failure(RuntimeException("Something went wrong")))
        }

        //the catch block can be used to handle more sophisticated errors by using the mapper class
    }

}
