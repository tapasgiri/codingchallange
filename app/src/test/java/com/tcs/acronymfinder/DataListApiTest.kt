package com.tcs.acronymfinder

import com.tcs.acronymfinder.network.RetroService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataListApiTest {

    lateinit var mockWebServer:MockWebServer
    lateinit var apiService: RetroService

    @Before
    fun setUp(){
      mockWebServer = MockWebServer()
      apiService = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
          .addConverterFactory(GsonConverterFactory.create())
          .build().create(RetroService::class.java)
    }

    @Test
    fun testGetData() = runTest{
       val mockResponse = MockResponse()

        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getData("")
        mockWebServer.takeRequest()

        Assert.assertEquals(true,response.body()!!.isEmpty())
    }

    @After
    fun tearDown(){
       mockWebServer.shutdown()
    }
}