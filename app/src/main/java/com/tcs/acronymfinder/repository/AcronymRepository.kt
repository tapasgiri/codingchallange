package com.tcs.acronymfinder.repository
import com.tcs.acronymfinder.model.Lfs
import com.tcs.acronymfinder.model.RecyclerList
import com.tcs.acronymfinder.network.RetroInstance
import com.tcs.acronymfinder.network.RetroService
import com.tcs.acronymfinder.util.NetworkResult

class AcronymRepository{

    suspend fun getData(newText: String): NetworkResult<List<RecyclerList>> {
        val retroInstance = RetroInstance.getRetroInstance().
        create(RetroService::class.java)
        val response = retroInstance.getData(newText)
        return if(response.isSuccessful){
            val responseBody = response.body()
            if (responseBody != null){
                NetworkResult.Success(responseBody)
            }else{
                NetworkResult.Error("Something went wrong")
            }
        }else{
            NetworkResult.Error("Something went wrong")
        }
    }
}