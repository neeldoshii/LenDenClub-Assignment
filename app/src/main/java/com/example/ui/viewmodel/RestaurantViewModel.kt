package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.RestaurantItem
import com.example.data.model.Result
import com.example.data.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repository: RestaurantRepository) : ViewModel(){

    private val _restaurantListResponse = MutableStateFlow<Result<List<RestaurantItem>>?>(null)
    val restaurantListResponse: StateFlow<Result<List<RestaurantItem>>?> = _restaurantListResponse

    fun getRestaurantList(){
        viewModelScope.launch {
            val response = repository.getRestaurantsResponse()
            println(response)
            _restaurantListResponse.emit(response)
        }
    }
}