package com.example.data.mapper



import android.util.Log
import com.example.data.dto.GenreDto
import com.example.data.dto.GenreItemDto
import com.example.domain.model.GenreItemModel
import com.example.domain.model.GenreModel
import javax.inject.Inject

class GenreMapper  @Inject constructor(){



    fun fromRemoteGenreToGenreModel(obj: GenreDto): GenreModel {
        return  GenreModel(
            genres = obj.genres.map {
                fromRemoteItemGenreToItemGenreMode(it)
            }
        )

    }


      private fun fromRemoteItemGenreToItemGenreMode(obj: GenreItemDto): GenreItemModel {
        return GenreItemModel(
                id = obj.id,
                name = obj.name,
            )
    }

}