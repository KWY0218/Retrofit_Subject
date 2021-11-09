package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Body
import com.example.myapplication.model.StatusCode
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel(){
    val myResponse:MutableLiveData<Response<StatusCode>> = MutableLiveData()
    val body:MutableLiveData<Response<Body>> = MutableLiveData()

    fun getStatusCode(s:String){
        viewModelScope.launch {
            val response = repository.getStatusCode(s)
            myResponse.value = response
        }
    }

    fun getBody(b:String){
        viewModelScope.launch {
            val response = repository.getBody(b)
            body.value = response
        }
    }
}

class MainViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}