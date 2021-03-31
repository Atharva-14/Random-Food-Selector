package com.example.randomfoodselector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(val list: List<String>, private val onClickListener: ItemClicked) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView.text = currentItem
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ItemClicked {
    fun onItemClick(string: String)
}