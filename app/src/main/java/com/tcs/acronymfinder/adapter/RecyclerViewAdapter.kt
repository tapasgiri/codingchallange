package com.tcs.acronymfinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcs.acronymfinder.R
import com.tcs.acronymfinder.model.Lfs
import com.tcs.acronymfinder.model.RecyclerList


class RecyclerViewAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var items = ArrayList<Lfs> ()
    fun setUpdatedData(items:ArrayList<Lfs>){
        this.items = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).
            inflate(R.layout.list_layout,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {

        return items.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tv_name)
    val tvDesc= itemView.findViewById<TextView>(R.id.tv_desc)
  fun bind(data: Lfs){
      tvName.text = data.lf
      tvDesc.text = data.since
  }
}
