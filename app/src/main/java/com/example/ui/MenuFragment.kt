package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Result
import com.example.myapplication.databinding.FragmentMenuBinding
import com.example.ui.adapter.MenuAdapter
import com.example.ui.viewmodel.MenuViewModel
import com.example.utils.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModels()
    private lateinit var adapter: MenuAdapter
    private var restaurantId : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        restaurantId = arguments?.getInt(Constants.KEY_RESTAURANT_ID)
        // Inflate the layout for this fragment

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MenuAdapter{
            viewModel.addToCart(it)

        }
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        restaurantId?.let { viewModel.getMenuList(restaurantId = it) }
        lifecycleScope.launch {
            viewModel.menuListResponse.collect{response ->
                when (response) {
                    is Result.Success -> {
                        adapter.submitList(response.data)
                    }

                    is Result.Error -> {
                        Snackbar.make(requireView(), response.message, Snackbar.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}