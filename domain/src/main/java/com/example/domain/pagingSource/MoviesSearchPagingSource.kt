package com.example.domain.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.MovieItem
import com.example.domain.repository.MovieSearchRepository
import com.example.domain.repository.MoviesRepository


class MoviesSearchPagingSource(
    private val repository: MovieSearchRepository,
    val movieName: String,
    val language: String,
): PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val page = params.key ?: 1
            val response = repository.getRemoteSearchMovie(movieName = movieName,page= page, language = language)

            LoadResult.Page(
                data = response.moveList,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.moveList.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}