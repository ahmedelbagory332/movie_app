package com.example.movieApp.domain



import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUseCase   {
    operator fun invoke(repository: FakeRepo): Flow<String> = flow {
        try {
            repository.getData().map {
                emit(it)
            }
        } catch(e: Exception) {
            emit("${e.localizedMessage} : An unexpected error happened")
        }
    }
}