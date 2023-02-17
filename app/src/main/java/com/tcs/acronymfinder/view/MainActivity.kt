
package com.tcs.acronymfinder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.acronymfinder.StoreApplication
import com.tcs.acronymfinder.adapter.RecyclerViewAdapter
import com.tcs.acronymfinder.databinding.ActivityMainBinding
import com.tcs.acronymfinder.repository.AcronymRepository
import com.tcs.acronymfinder.util.NetworkResult
import com.tcs.acronymfinder.util.Utils
import com.tcs.acronymfinder.viewModel.MainViewModel
import com.tcs.acronymfinder.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)

        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = recyclerViewAdapter

        val repository = (application as StoreApplication).dataRepository

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).
        get(MainViewModel::class.java)

        mainViewModel.items.observe(this, Observer {
            when(it){
                is NetworkResult.Success->{
                    recyclerViewAdapter.addData(it.data!![0].lfs)
                }
                is NetworkResult.Error->{}
            }
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        recyclerViewAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText.length > 2) {
            if(Utils.isOnline(this))
                mainViewModel.getItems(newText)
            else
                showMessage()
        }
        recyclerViewAdapter.filter.filter(newText)
        return false
    }

    private fun showMessage() {
        Toast.makeText(this,"No internet",Toast.LENGTH_SHORT).show()
    }
}