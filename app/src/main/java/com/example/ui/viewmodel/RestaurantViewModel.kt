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

    private val _filterText = MutableStateFlow<List<RestaurantItem>>(emptyList())
    val filterText: StateFlow<List<RestaurantItem>>
        get() = _filterText

    private val _noResultsFound = MutableStateFlow(false)
    val noResultsFound: StateFlow<Boolean> get() = _noResultsFound

    fun getRestaurantList(){
        viewModelScope.launch {
            val response = repository.getRestaurantsResponse()
            println(response)
            _restaurantListResponse.emit(response)
        }
    }

    fun performSearch(query : String) {
        (_restaurantListResponse.value as? Result.Success)?.let {
            val filteredList = it.data.filter {
                it.name?.contains(query, ignoreCase =true) == true

            }
            _filterText.value = filteredList
            if (filteredList.isEmpty()) {
                _noResultsFound.value = true  // Set the flag to true if no results are found
            } else {
                _noResultsFound.value = false  // Reset the flag if results are found
            }
        } ?: run {
            // If the result is not a Success or is null, we reset the filter list to empty
            _filterText.value = emptyList()
        }
    }
}