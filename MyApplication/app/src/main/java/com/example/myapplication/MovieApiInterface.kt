package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("3/movie/latest?api_key=f1bbbddac88dd9cfd727c2673fe987c8&language=en-US")
    fun getLatestMovieList(
//        @Query("page") page: Int
    ) : Call<MovieLatest>

    @GET("3/movie/top_rated?api_key=f1bbbddac88dd9cfd727c2673fe987c8&language=en-US&page=1")
    fun getTopRatedMovieList(
        @Query("page") page: Int
    ) : Call<MovieTopRated>

    @GET("3/movie/upcoming?api_key=f1bbbddac88dd9cfd727c2673fe987c8&language=en-US")
    fun getUpcomingMovieList(
        @Query("page") page : Int
    ) : Call<MovieUpcoming>
}