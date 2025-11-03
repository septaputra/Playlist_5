package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.network.RetrofitInstance
import com.example.weatherapp.data.network.Constants
import com.example.weatherapp.model.NetworkResponse
import com.example.weatherapp.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getWeather(Constants.API_KEY, city)
                if (response.isSuccessful && response.body() != null) {
                    _weatherResult.postValue(NetworkResponse.Success(response.body()!!))
                } else {
                    _weatherResult.postValue(NetworkResponse.Error(response.message()))
                }
            } catch (e: Exception) {
                _weatherResult.postValue(NetworkResponse.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
