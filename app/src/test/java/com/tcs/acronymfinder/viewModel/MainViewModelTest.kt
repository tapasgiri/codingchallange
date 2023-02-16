package com.tcs.acronymfinder.viewModel

import com.tcs.acronymfinder.repository.AcronymRepository
import com.tcs.acronymfinder.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repository: AcronymRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun test_GetItems() = runTest{
      Mockito.`when`(repository.getData("")).thenReturn(NetworkResult.Success(emptyList()))
        val sut = MainViewModel(repository)
        sut.getItems("")
        val result = sut.items.value
        Assert.assertEquals(0, result!!.data!!.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}