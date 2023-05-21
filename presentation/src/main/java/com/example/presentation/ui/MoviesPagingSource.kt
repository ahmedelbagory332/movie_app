//package com.example.presentation.ui
//
//import androidx.lifecycle.viewModelScope
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.domain.model.MovieModel
//import com.example.domain.use_case.MoviesUseCase
//import com.example.domain.utils.MovieState
//import com.example.domain.utils.Resource
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//
//
//class MoviesPagingSource(
//    val getMoviesUseCase: MoviesUseCase,
//): PagingSource<Int, MovieModel>() {
//    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
//        return try {
//            val page = params.key ?: 1
//            val response = getMoviesUseCase(page ,"28").onEach { result ->
//                when (result) {
//                    is Resource.Success -> {
//
//                        _state.value = MovieState(
//                            movies = result.data ?: MovieModel()
//                        )
//
//                    }
//                    is Resource.Error -> {
//                        _state.value = MovieState(
//                            error = result.message ?: "An unexpected error happened"
//                        )
//                    }
//                    is Resource.Loading -> {
//                        _state.value = MovieState(isLoading = true)
//                    }
//                }
//            }.launchIn(viewModelScope)
//
//            LoadResult.Page(
//                data = response.articles,
//                prevKey = if (page == 1) null else page.minus(1),
//                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}