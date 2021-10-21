package com.byfreakdevs.fitme.utlis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.models.Item

class DashboardRecyclerViewAdapter  : RecyclerView.Adapter<DashboardRecyclerViewAdapter.ViewHolder>()  {

    private val mList = ArrayList<Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_recyclerview_items , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardRecyclerViewAdapter.ViewHolder, position: Int) {

        val item = mList[position]

        holder.food.text = item.name
        holder.calories.text = item.calories.toString()
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun setFoodList(food: List<Item>) {
        mList.clear()
        mList.addAll(food)
        notifyDataSetChanged()
    }



    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val food: TextView = itemView.findViewById(R.id.tvFood)
        val calories: TextView = itemView.findViewById(R.id.tvCalories)

    }
}