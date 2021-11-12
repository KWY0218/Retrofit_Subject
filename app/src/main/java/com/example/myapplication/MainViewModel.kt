package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.LectureDetails
import com.example.myapplication.model.LectureResult
import com.example.myapplication.model.StatusCode
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel(){
    val myResponse:MutableLiveData<Response<StatusCode>> = MutableLiveData()
    val lectureList:MutableLiveData<Response<LectureResult>> = MutableLiveData()
    val lectureDetails:MutableLiveData<Response<LectureDetails>> = MutableLiveData()

    fun getStatusCode(){
        viewModelScope.launch {
            val response = repository.getStatusCode()
            myResponse.value = response
        }
    }

    fun getHangangList(limit:Int,page:Int){
        viewModelScope.launch {
            val response = repository.getHangangList(limit,page)
            lectureList.value = response
        }
    }

    fun getDetail(id:Int){
        viewModelScope.launch {
            val response = repository.getDetail(id)
            lectureDetails.value = response
        }
    }
}

class MainViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}