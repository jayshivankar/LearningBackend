package com.example.learningretrofit

import android.net.http.HttpException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningretrofit.databinding.ActivityMainBinding
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val todoAdapter = TodoAdapter()
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            }catch (e: IOException){
                Log.e(TAG,"An error occurred: ${e.message}")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"An error occurred: ${e.message}")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                todoAdapter.todos = response.body()!!
            }else{
                Log.e(TAG,"An error occurred: ${response.message()}")
            }
            binding.progressBar.isVisible = false

        }
    }
    private fun setupRecyclerView() = binding.rvTodos.apply{
        binding.rvTodos.adapter = todoAdapter
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}