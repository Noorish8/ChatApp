package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityDashBoardBinding
import com.example.chatapp.databinding.ActivityMainBinding

class DashBoard : AppCompatActivity() {
    lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}