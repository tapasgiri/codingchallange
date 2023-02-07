package com.tcs.acronymfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcs.acronymfinder.R
import com.tcs.acronymfinder.databinding.ListLayoutBinding
import com.tcs.acronymfinder.model.Lfs


class RecyclerViewAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var items = ArrayList<Lfs> ()
    fun setUpdatedData(items:ArrayList<Lfs>){
        this.items = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context)
       val binding = ListLayoutBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(items.get(position))
    }

    override fun getItemCount() = items.size
}

class MyViewHolder(val binding: ListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
   /* val tvName = itemView.findViewById<TextView>(R.id.tv_name)
    val tvDesc= itemView.findViewById<TextView>(R.id.tv_desc)*/
  fun bind(data: Lfs){
       binding.recyclerData = data
       binding.executePendingBindings()
  }
}
