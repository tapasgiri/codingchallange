package com.tcs.acronymfinder.network

import com.tcs.acronymfinder.model.Lfs
import com.tcs.acronymfinder.model.RecyclerList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("dictionary.py")
    suspend fun getData(@Query("sf") sf:String): Response<List<RecyclerList>>
}