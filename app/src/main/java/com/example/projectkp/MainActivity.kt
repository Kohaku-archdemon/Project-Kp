package com.example.projectkp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkp.databinding.ActivityLoginPageBinding
import com.example.projectkp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginmainbutton.setOnClickListener {
            startActivity(Intent(this, loginPage::class.java))
            finish()
        }

        binding.signupmainbutton.setOnClickListener {
            startActivity(Intent(this, loginPage::class.java))
            finish()
        }

    }
}