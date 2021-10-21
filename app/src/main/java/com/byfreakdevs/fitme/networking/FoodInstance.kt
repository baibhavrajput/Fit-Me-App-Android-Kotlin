package com.byfreakdevs.fitme.networking

import com.byfreakdevs.fitme.models.GetFood
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//const val API_KEY = "9zR7ZNct/Ts8KdtSpja42g==M6qKwwfDVRZhAxN1"
//private const val BASE_URL = "https://api.calorieninjas.com/"


interface FoodInstance {

    @Headers("X-Api-Key: 9zR7ZNct/Ts8KdtSpja42g==Xl8G5gPcWcKJHjza")
    @GET("/v1/nutrition")
    suspend  fun getFoodNutrition(@Query("query") query : String) : Response<GetFood>
}

//object FoodService {
//    val foodInstance : FoodInstance
//    init {
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor())
//            .addInterceptor(PlutoInterceptor())
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        foodInstance = retrofit.create(FoodInstance::class.java)
//    }
//}

