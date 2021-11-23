package com.example.retrofithilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofithilt.databinding.ActivityMainBinding
import com.example.retrofithilt.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = CommentRepository(ApiService())
        factory = CommentViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
            binding.recyclerView.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = CommentAdapter(comments)

            }
        }

}

