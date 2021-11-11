package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityLectureDetailsBinding
import com.example.myapplication.repository.Repository

class LectureDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLectureDetailsBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("lecture_id", 1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lecture_details)
        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository()))
            .get(MainViewModel::class.java)

        with(binding) {
            with(viewModel) {
                getDetail(id)
                lectureDetails.observe(this@LectureDetailsActivity, Observer { response ->
                    if (response.isSuccessful) {
                        Log.d("Response1","details name: " + response.body()?.lectureDetailsName)
                        lectureDetailsName.text = response.body()?.lectureDetailsName
                    } else {
                        Log.d("Response1", "status: " + response.errorBody().toString())
                        Log.d("Response1", "status: " + response.code().toString())
                    }
                })
            }

        }
    }
}