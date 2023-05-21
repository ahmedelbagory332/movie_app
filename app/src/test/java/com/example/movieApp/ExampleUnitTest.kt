package com.example.movieApp

import com.example.movieApp.data.FakeRepoImpl
import com.example.movieApp.domain.FakeRepo
import com.example.movieApp.domain.FakeUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var list = listOf<String>("movie 1","movie 2","movie 3","movie 4","movie 5","movie 6","movie 7")
    val fakeRepo: FakeRepo = FakeRepoImpl()
    val getData = fakeRepo.getData()

    @Test
    fun testDataFromRepo() {

        assertEquals(getData.last(),list.last())
        assertEquals(getData.first(),list.first())
        assertEquals(getData.size,list.size)
    }

    @Test
    fun testDataFromUseCase() {

        val fakeUseCase = FakeUseCase()
        runBlocking {

             fakeUseCase(fakeRepo).collect {
                 assertTrue(list.contains(it))
            }


        }


    }
}