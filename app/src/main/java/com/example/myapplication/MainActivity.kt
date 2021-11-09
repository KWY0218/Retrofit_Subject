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
        viewModel.getStatusCode()

        viewModel.myResponse.observe(this, Observer { response->
            if(response.isSuccessful){
                Log.d("Response1","status: "+response.body()?.statusCode.toString())
                Log.d("Response1","status: "+response.body()?.body?.bodyInForce.toString())
                Log.d("Response1","status: "+response.body()?.body?.bodyInVersion.toString())
            }else{
                Log.d("Response1","status: "+response.errorBody().toString())
                Log.d("Response1","status: "+response.code().toString())
            }
        })
    }
}