package com.byfreakdevs.fitme.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byfreakdevs.fitme.repository.FoodRepository
import com.byfreakdevs.fitme.models.Item
import kotlinx.coroutines.*

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Item>>()

     fun getFood(foodName : String){
        CoroutineScope(Dispatchers.IO).launch {

            val response = foodRepository.getFood(foodName)

            withContext(Dispatchers.Main){
                if(response.isSuccessful ){
                    foodList.value = response.body()!!.items
                }
                else{
                    Log.d("baibhav" , "error")
                }
            }
        }
    }

//    val allFood : LiveData<List<Item>> = foodRepository.itemList
//
//    fun insert(item : Item) = viewModelScope.launch(Dispatchers.IO) {
//        foodRepository.insert(item)
//    }



}
//    val foodList = MutableLiveData<List<GetFoodItem>>()
//
//    fun getFood(foodName : String ) {
//
//        CoroutineScope(Dispatchers.IO).launch{
////            val food = foodRepository.getFood(foodName)
//////            val food = FoodService.foodInstance.getFoodNutrition("$foodSearched")
////
////            food.enqueue(object : Callback<GetFood?> {
////                override fun onResponse(call: Call<GetFood?>, response: Response<GetFood?>) {
////
//////                    val res = response.body()?.get(0)
//////                    val calories = res?.calories
////                    foodList.postValue(response.body())
////
////                }
////
////                override fun onFailure(call: Call<GetFood?>, t: Throwable) {
////                    TODO("Not yet implemented")
////                }
////            })
////        }
//
////        CoroutineScope(Dispatchers.IO).launch {
////            val response = foodRepository.getFood(foodName)
////            withContext(Dispatchers.Main){
////                if(response.isSuccessful){
////                    food.value = listOf(response.body()!![0])
////                }else{
////                    Log.d("Baibhav" , "Error fetching data")
////                }
////            }
////        }
////    }
//
//
////    init {
////        viewModelScope.launch(Dispatchers.IO ){
////            foodRepository.getFood("egg")
////        }
////
////    }
////
////    val food : LiveData<GetFood>
////    get() = foodRepository.food
//
//
////    var food = MutableLiveData<List<FoodItem>>()
////
////    fun getFood(foodName : String){
////
////        CoroutineScope(Dispatchers.IO).launch(){
////
////            val response = foodRepository.getFood(foodName)
////            withContext(Dispatchers.Main){
////                if(response.isSuccessful){
////                    food.value = response.body()!!.foodItems
////                }
////                else{
////                    Log.d("Baibhav" , "Error fetching data")
////                }
////            }
////        }
//    }
//}