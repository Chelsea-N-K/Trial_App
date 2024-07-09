package com.example.trial_app

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trial_app.dashboard.Dashboard


class LoginActivity : AppCompatActivity() {

        private lateinit var dbHelper: DBHelper
        private lateinit var sharedPreferences: SharedPreferences

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            dbHelper = DBHelper(this)
            sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

            val edtEmail: EditText = findViewById(R.id.edtProviderEmail)
            val edtPassword: EditText = findViewById(R.id.edtProviderPassword)
            val btnLogin: Button = findViewById(R.id.loginButton)

            btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    if (dbHelper.authenticateUser(email, password)) {
                        val savedEmail = sharedPreferences.getString("email", "")
                        val savedPassword = sharedPreferences.getString("password", "")

                        if (email == savedEmail && password == savedPassword) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            // Proceed to the next activity
                            startActivity(Intent(this, Dashboard::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

