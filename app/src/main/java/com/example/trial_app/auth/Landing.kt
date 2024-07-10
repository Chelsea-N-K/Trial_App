package com.example.trial_app.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trial_app.R
import com.example.trial_app.dashboard.Dashboard

class Landing : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        startTimer()
    }
    private fun startTimer() {

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // Code to be executed after 3 seconds
            println("Timer finished")
            Toast.makeText(this@Landing, "Timer Finished", Toast.LENGTH_SHORT).show()
            val loggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
            if (loggedIn){
                startActivity(Intent(this@Landing, Dashboard::class.java))
            } else{
                startActivity(Intent(this@Landing, LoginActivity::class.java))
            }
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}