package com.example.chatapp.ui.main.studant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityClassDetailsBinding

class ClassDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityClassDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)

        binding=ActivityClassDetailsBinding.inflate(layoutInflater)
        binding.videoView1.setVideoPath(
            "http://www.ebookfrenzy.com/android_book/movie.mp4")

        binding.videoView1.start()
        setContentView(binding.root)
    }




}