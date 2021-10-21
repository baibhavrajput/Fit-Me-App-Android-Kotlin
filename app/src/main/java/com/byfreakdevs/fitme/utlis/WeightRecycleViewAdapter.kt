package com.byfreakdevs.fitme.utlis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.models.WeightItemViewModel


class WeightRecycleViewAdapter(private val mList : List<WeightItemViewModel>) : RecyclerView.Adapter<WeightRecycleViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weight_list_items , parent , false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeightRecycleViewAdapter.ViewHolder, position: Int) {

        val weightItemViewModel = mList[position]

        holder.date.text = weightItemViewModel.date
        holder.weight.text = weightItemViewModel.weight
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val date: TextView = itemView.findViewById(R.id.tvDate)
        val weight: TextView = itemView.findViewById(R.id.tvWeight)

    }
}