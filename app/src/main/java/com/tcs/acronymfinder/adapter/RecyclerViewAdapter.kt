package com.tcs.acronymfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcs.acronymfinder.R
import com.tcs.acronymfinder.databinding.ListLayoutBinding
import com.tcs.acronymfinder.model.Lfs


class RecyclerViewAdapter : RecyclerView.Adapter<MyViewHolder>(), Filterable {
    var items = ArrayList<Lfs> ()
    var filteredItems = ArrayList<Lfs> ()

    fun addData(list: List<Lfs>) {
        items = list as ArrayList<Lfs>
        filteredItems = items
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

    override fun getFilter(): Filter {

        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredItems = items
                } else {
                    val resultList = ArrayList<Lfs>()
                    items
                        .filter {
                            (it.lf.contains(constraint!!))

                        }
                        .forEach { filteredItems.add(it) }
                    filteredItems = resultList
                    // countryFilterList = resultList
                }

                return FilterResults().apply { values = filteredItems }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                filteredItems = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Lfs>
                notifyDataSetChanged()
            }

        }
    }
}

class MyViewHolder(val binding: ListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(data: Lfs){
       binding.recyclerData = data
       binding.executePendingBindings()
  }
}
