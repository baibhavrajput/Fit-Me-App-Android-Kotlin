package com.byfreakdevs.fitme.utlis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.models.Item

class DashboardRecyclerViewAdapter (private val foodDetailsArrayList: ArrayList<FoodDetails>)
    : RecyclerView.Adapter<DashboardRecyclerViewAdapter.FoodDetailsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FoodDetailsHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.dashboard_recyclerview_items, viewGroup, false)
        return FoodDetailsHolder(view)
    }

    override fun onBindViewHolder(foodDetailsHolder: FoodDetailsHolder, i: Int) {

        val currentFood = foodDetailsArrayList[i]
        foodDetailsHolder.tvFood.text = currentFood.name
        foodDetailsHolder.tvCalories.text = currentFood.calories.toString()
    }

    override fun getItemCount(): Int {
        return foodDetailsArrayList.size
    }

    class FoodDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvFood: TextView = itemView.findViewById(R.id.tvFood)
        var tvCalories: TextView = itemView.findViewById(R.id.tvCalories)

    }
}
