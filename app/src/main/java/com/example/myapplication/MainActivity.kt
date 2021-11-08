package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response->
            if(response.isSuccessful){
                Log.d("Response1",response.code().toString())
                Log.d("Response1",response.body()?.version.toString())
                Log.d("Response1",response.body()?.force.toString())
            }else{
                Log.d("Response1",response.errorBody().toString())
                Log.d("Response1",response.code().toString())
            }
        })
    }
}