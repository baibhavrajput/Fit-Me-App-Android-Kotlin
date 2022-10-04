package com.byfreakdevs.fitme.networking

import com.byfreakdevs.fitme.networking.FoodInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodService {
    private const val BASE_URL = "https://api.calorieninjas.com/"

    fun getInstance() : FoodInstance {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(FoodInstance::class.java)
    }
}