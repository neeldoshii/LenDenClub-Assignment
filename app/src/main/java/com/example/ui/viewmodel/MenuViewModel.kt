package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.MenuItem
import com.example.data.model.Result
import com.example.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel(){

    private val _menuListResponse = MutableStateFlow<Result<List<MenuItem>>?>(null)
    val menuListResponse: StateFlow<Result<List<MenuItem>>?> = _menuListResponse


    fun getMenuList(restaurantId : Int){
        viewModelScope.launch {
            val response = repository.getRestaurantsMenuResponse(restaurantId = restaurantId)
            println(response)
            _menuListResponse.emit(response)
        }
    }
}