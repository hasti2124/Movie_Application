package com.example.movieapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("now_playing")
    fun getMovieInterface(
        @Query("page")page:Int
    ) : Call<MovieModel>
}