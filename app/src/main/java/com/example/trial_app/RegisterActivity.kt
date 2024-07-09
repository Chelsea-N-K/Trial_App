package com.example.trial_app

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.provider_registration)

        dbHelper = DBHelper(this@RegisterActivity)
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val providerFirstName: EditText = findViewById(R.id.providerFirstName)
        val providerLastName: EditText = findViewById(R.id.providerLastName)
        val providerTelephone: EditText = findViewById(R.id.providerTelephone)
        val providerEmail: EditText = findViewById(R.id.providerEmail)
        val providerPassword: EditText = findViewById(R.id.providerPassword)
        val providerConfirmPassword: EditText = findViewById(R.id.providerConfirmPassword)
        val spinnerRole: Spinner = findViewById(R.id.spinnerRole)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        // Populate spinner with role options
        val roleOptions = arrayOf("Nurse", "Doctor", "Dentist", "Oncologist")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roleOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter

        btnRegister.setOnClickListener {
            val firstName = providerFirstName.text.toString()
            val lastName = providerLastName.text.toString()
            val email = providerEmail.text.toString()
            val telephone = providerTelephone.text.toString()
            val password = providerPassword.text.toString()
            val confirmPassword = providerConfirmPassword.text.toString()
            val role = spinnerRole.selectedItem.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || telephone.isEmpty()
                || role.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val userId =
                    dbHelper.registerProvider(firstName, lastName, email, telephone, password, role)
                if (userId > -1) {
                    // Save user info in SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("email", email)
                    editor.putString("password", password)
                    editor.putString("roleOptions", role)
                    editor.apply()

                    Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}