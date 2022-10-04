package com.byfreakdevs.fitme.repository

import com.byfreakdevs.fitme.networking.FoodInstance

class FoodRepository(private val foodInstance: FoodInstance) {


    suspend fun getFood(foodName : String) = foodInstance.getFoodNutrition(foodName)

//    suspend fun insert(item: Item) = dao.insert(item)
//
//    suspend fun delete(item: Item) = dao.delete(item)
//
//    val itemList : LiveData<List<Item>> = dao.getAllNotes()

}
