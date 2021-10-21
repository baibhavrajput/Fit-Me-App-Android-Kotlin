package com.byfreakdevs.fitme.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byfreakdevs.fitme.repository.FoodRepository
import com.byfreakdevs.fitme.viewModels.FoodViewModel

class FoodViewModelFactory(private val foodRepository: FoodRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            FoodViewModel(this.foodRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
