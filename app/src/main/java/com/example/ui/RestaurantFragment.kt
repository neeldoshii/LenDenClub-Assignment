package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Result
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRestaurantBinding
import com.example.ui.adapter.RestaurantAdapter
import com.example.ui.viewmodel.RestaurantViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var adapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restaurantRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RestaurantAdapter {
            val bundle = Bundle().apply {
                putString("restaurantId", it.toString()) // TODO : use const
            }
            findNavController().navigate(R.id.action_restaurantFragment_to_menuFragment, bundle)
        }


        binding.restaurantRecyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.restaurantListResponse.collect { response ->
                when (response) {
                    is Result.Success -> {
                        adapter.submitList(response.data)
                    }

                    is Result.Error -> {
                        // TODO : show cached response from Room DB
                        println("")
                    }

                    else -> {}
                }
            }
        }

        binding.searchRestaurant.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // We are not using this as we are performing search on every new character changed
            // not on when submit
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.performSearch(query = it)
                }

                return true
            }

        })


        lifecycleScope.launch {
            viewModel.filterText.collect { filteredList ->
                adapter.submitList(filteredList)
            }
        }

        lifecycleScope.launch {
            viewModel.noResultsFound.collect { noResults ->
                if (noResults) {
                    // if nothing found from search then show a toast saying not found
                    Toast.makeText(requireContext(), "No Result Found", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getRestaurantList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}