package com.example.movieApp.data

import com.example.movieApp.domain.FakeRepo
import kotlinx.coroutines.flow.Flow
class FakeRepoImpl : FakeRepo {

    override fun getData(): List<String> = DataSource.list

}