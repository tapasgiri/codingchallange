
package com.tcs.acronymfinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.acronymfinder.adapter.RecyclerViewAdapter
import com.tcs.acronymfinder.databinding.FragmentMainBinding
import com.tcs.acronymfinder.util.Utils
import com.tcs.acronymfinder.viewModel.MainActivityViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var recyclerViewAdapter:RecyclerViewAdapter
    private lateinit var searchBtn:Button
    private lateinit var etInput:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(layoutInflater)
       // val view = inflater.inflate(R.layout.fragment_main, container, false)
        initView(binding)
        initViewModel()
        return binding.root
    }

    private fun initView(root: FragmentMainBinding) {
        etInput = root.svSearch
        searchBtn = root.btnSearch
        val recyclerView = root.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter

    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer {
            if (it!=null){
                recyclerViewAdapter.setUpdatedData(it.lfs)
            }else{
                Toast.makeText(activity,"Error in getting data",Toast.LENGTH_SHORT).show()
            }
        })
        searchBtn.setOnClickListener(View.OnClickListener {
            if(Utils.isOnline(requireContext()))
             viewModel.makeApiCall(etInput.query.toString())
            else
                Toast.makeText(activity,"No internet",Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }
}