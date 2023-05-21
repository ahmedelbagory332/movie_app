package com.example.movieApp



import com.example.data.mapper.GenreMapper
import com.example.data.mapper.MovieDetailsMapper
import com.example.data.mapper.MovieMapper
import com.example.data.mapper.SearchMovieMapper
import com.example.data.remote.MovieApi
import com.example.data.repository.GenreRepositoryImpl
import com.example.data.repository.MovieDetailsRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.data.repository.MovieSearchRepositoryImpl
import com.example.domain.repository.GenreRepository
import com.example.domain.repository.MovieDetailsRepository
import com.example.domain.repository.MovieSearchRepository
import com.example.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)



    @Provides
    @Singleton
    fun genreRepository(
        api: MovieApi,
        genreMapper: GenreMapper
    ): GenreRepository =
        GenreRepositoryImpl(api,genreMapper)

    @Provides
    @Singleton
    fun movieRepositoryImpl(
        api: MovieApi,
        movieMapper: MovieMapper
    ): MoviesRepository =
        MovieRepositoryImpl(api,movieMapper)

    @Provides
    @Singleton
    fun movieDetailsRepository(
        api: MovieApi,
        movieDetailsMapper: MovieDetailsMapper
    ): MovieDetailsRepository =
        MovieDetailsRepositoryImpl(api,movieDetailsMapper)

    @Provides
    @Singleton
    fun movieSearchRepositoryImpl(
        api: MovieApi,
        movieMapper: SearchMovieMapper
    ): MovieSearchRepository =
        MovieSearchRepositoryImpl(api,movieMapper)

}