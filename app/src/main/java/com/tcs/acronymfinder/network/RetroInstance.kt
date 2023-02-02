package com.tcs.acronymfinder.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        const val base_url = "http://www.nactem.ac.uk/software/acromine/"

        fun getRetroInstance(): Retrofit{

            return Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}