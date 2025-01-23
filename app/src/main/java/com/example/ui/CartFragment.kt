package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCartBinding
import com.example.ui.adapter.CartAdapter
import com.example.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CartAdapter(
            onIncrement = {
                viewModel.incrementQuantity(it)
                viewModel.getCartItems()
            },
            onDecrement = {
                viewModel.decrementQuantity(it)
                viewModel.getCartItems()
            }
        )
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.cartItem.collect{
                adapter.submitList(it)
            }
        }
        viewModel.getCartItems()
        lifecycleScope.launch {
            viewModel.totalAmount.collect{
                binding.totalAmountTextView.text = "Total Amount : Rs $it" // TODO use string format
            }
        }
        binding.checkoutButton.setOnClickListener{
            findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}