package com.derek.recycleviewstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val fruitsList: List<Fruits>, private val clickListener: (Fruits) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fruitsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(fruit: Fruits, clickListener: (Fruits) -> Unit) {
        view.name_text_view.text = fruit.name
        view.setOnClickListener { clickListener(fruit) }
    }
}