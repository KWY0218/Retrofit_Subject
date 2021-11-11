package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.HanGangAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.Repository

class MainActivity: AppCompatActivity(),GoActivity {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var hangangAdapter:HanGangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        hangangAdapter = HanGangAdapter(this)
        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository()))
            .get(MainViewModel::class.java)

        with(binding) {

            with(recyclerview) {
                adapter = hangangAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            with(viewModel) {
                getHangangList(10, Integer.parseInt(page.text.toString()))
                lectureList.observe(this@MainActivity, Observer { response ->
                    if (response.isSuccessful) {
                        response.body()?.resultList.let {
                            hangangAdapter.submitList(it)
                        }

                        response.body()?.resultList?.forEach {
                            Log.d("Response1", "id: " + it.id)
                            Log.d("Response1", "professor: " + it.professor)
                            Log.d("Response1", "subjectName: " + it.subjectName)
                            Log.d("Response1", "-----------------------------------")
                        }

                    } else {
                        Log.d("Response1", "status: " + response.errorBody().toString())
                        Log.d("Response1", "status: " + response.code().toString())
                    }

                })
            }


            rightBtn.setOnClickListener {
                if (Integer.parseInt(page.text.toString()) + 1 < 102) {
                    page.setText((Integer.parseInt(page.text.toString()) + 1).toString())
                    viewModel.getHangangList(10, Integer.parseInt(page.text.toString()))
                }
            }

            leftBtn.setOnClickListener {
                if (Integer.parseInt(page.text.toString()) - 1 > 0) {
                    page.setText((Integer.parseInt(page.text.toString()) - 1).toString())
                    viewModel.getHangangList(10, Integer.parseInt(page.text.toString()))
                }
            }
        }
    }

    override fun goActivity(id: Int?) {
        val intent = Intent(this,LectureDetailsActivity::class.java)
        intent.putExtra("lecture_id",id)
        startActivity(intent)
    }
}