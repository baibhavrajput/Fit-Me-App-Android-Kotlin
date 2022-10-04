package com.byfreakdevs.fitme.networking

import com.byfreakdevs.fitme.models.GetFood
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//const val API_KEY = "FcLT1GX3XcRLoisohN1nFDUxUG3rXrNOQoErR9tP"
//private const val BASE_URL = "https://api.calorieninjas.com/"


interface FoodInstance {

    @Headers("X-Api-Key: FcLT1GX3XcRLoisohN1nFDUxUG3rXrNOQoErR9tP")
    @GET("/v1/nutrition?")
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

