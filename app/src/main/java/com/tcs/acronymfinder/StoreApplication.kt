package com.tcs.acronymfinder

import android.app.Application
import com.tcs.acronymfinder.network.RetroService
import com.tcs.acronymfinder.repository.AcronymRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication:Application() {
    lateinit var dataListApi:RetroService
    lateinit var dataRepository: AcronymRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.nactem.ac.uk/software/acromine/")
            .build()

        dataListApi = retrofit.create(RetroService::class.java)
        dataRepository = AcronymRepository(dataListApi)
    }
}