package com.byfreakdevs.fitme.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byfreakdevs.fitme.repository.FoodRepository
import com.byfreakdevs.fitme.models.Item
import kotlinx.coroutines.*

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Item>>()

    fun getFood(foodName: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val response = foodRepository.getFood(foodName)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    foodList.value = response.body()!!.items
                }

                else {
                    Log.d("baibhav", "error")
                }
            }
        }
    }
}