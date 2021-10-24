package com.byfreakdevs.fitme.utlis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.byfreakdevs.fitme.R

class WeightRecyclerViewAdapter (private val weightDetailsArrayList: ArrayList<WeightDetails>)
    : RecyclerView.Adapter<WeightRecyclerViewAdapter.WeightDetailsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WeightDetailsHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.weight_recyclerview_items, viewGroup, false)
        return WeightDetailsHolder(view)
    }

    override fun onBindViewHolder(weightDetailsHolder: WeightDetailsHolder, i: Int) {

        val currentWeight = weightDetailsArrayList[i]
        weightDetailsHolder.tvDate.text = currentWeight.date
        weightDetailsHolder.tvWeight.text = currentWeight.weight

    }

    override fun getItemCount(): Int {
        return weightDetailsArrayList.size
    }

     class WeightDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var tvWeight: TextView = itemView.findViewById(R.id.tvWeight)

    }

}