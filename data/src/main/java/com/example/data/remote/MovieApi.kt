package com.example.data.remote



import com.example.data.Constant
import com.example.data.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {



    @GET("genre/movie/list?api_key=${Constant.ApiKey}")
    suspend fun getGenres(
        @Query("language") language: String = "en",
        ): GenreDto

    @GET("discover/movie?api_key=${Constant.ApiKey}")
    suspend fun getMovies(
        @Query("with_genres") genresId:String,
        @Query("page") page: Int,
        @Query("language") language: String = "en",
    ): MovieDto

    @GET("movie/{id}?api_key=${Constant.ApiKey}")
    suspend fun getMovieDetails(
        @Path("id") id:String,
        @Query("language") language: String = "en",
    ): MovieDetailsDto


    @GET("search/movie?api_key=${Constant.ApiKey}")
    suspend fun getSearchMovies(
        @Query("query") query:String,
        @Query("page") page: Int,
        @Query("language") language: String = "en",
    ): MovieDto




}