package com.example.trial_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trial_app.dashboard.Dashboard

class UpdatePatientActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_patient)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // Variables for the edit text, button, strings and dbHandler class.
        private lateinit var patientFirstNameEdt: EditText
        private lateinit var patientMiddleNameEdt: EditText
        private lateinit var patientLastNameEdt: EditText
        private lateinit var patientIdNumberEdt: EditText
        private lateinit var patientTelephoneEdt: EditText
        private lateinit var patientTemperatureEdt: EditText
        private lateinit var patientHeightEdt: EditText
        private lateinit var patientWeightEdt: EditText
        private lateinit var updatePatientBtn: Button
        private lateinit var deletePatientBtn: Button
        private lateinit var dbHandler: DBHandler
        private lateinit var patientFirstName: String
        private lateinit var patientMiddleName: String
        private lateinit var patientLastName: String
        private lateinit var patientIdNumber: String
        private lateinit var patientTelephone: String
        private lateinit var patientTemperature: String
        private lateinit var patientHeight: String
        private lateinit var patientWeight: String

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_update_patient)

            // Initializing all the variables.
            patientFirstNameEdt = findViewById(R.id.edtPatientFirstName)
            patientMiddleNameEdt = findViewById(R.id.edtPatientMiddleName)
            patientLastNameEdt = findViewById(R.id.edtPatientLastName)
            patientIdNumberEdt = findViewById(R.id.edtPatientIdNumber)
            patientTelephoneEdt = findViewById(R.id.edtPatientTelephone)
            patientTemperatureEdt = findViewById(R.id.edtPatientTemperature)
            patientHeightEdt = findViewById(R.id.edtPatientHeight)
            patientWeightEdt = findViewById(R.id.edtPatientWeight)
            updatePatientBtn = findViewById(R.id.btnUpdatePatient)
            deletePatientBtn = findViewById(R.id.btnDelete)

            // Initializing the dbHandler class.
            dbHandler = DBHandler(this@UpdatePatientActivity)

            // Getting data which was passed in the adapter class.
            patientFirstName = intent.getStringExtra("first_name") ?: ""
            patientMiddleName = intent.getStringExtra("middle_name") ?: ""
            patientLastName = intent.getStringExtra("last_name") ?: ""
            patientIdNumber = intent.getStringExtra("id_number") ?: ""
            patientTelephone = intent.getStringExtra("telephone") ?: ""
            patientTemperature = intent.getStringExtra("temperature") ?: ""
            patientHeight = intent.getStringExtra("height") ?: ""
            patientWeight = intent.getStringExtra("weight") ?: ""

            // Setting data to edit text of the update activity.
            patientFirstNameEdt.setText(patientFirstName)
            patientMiddleNameEdt.setText(patientMiddleName)
            patientLastNameEdt.setText(patientLastName)
            patientIdNumberEdt.setText(patientIdNumber)
            patientTelephoneEdt.setText(patientTelephone)
            patientTemperatureEdt.setText(patientTemperature)
            patientHeightEdt.setText(patientHeight)
            patientWeightEdt.setText(patientWeight)

            // Adding on click listener to the update patient button.
            updatePatientBtn.setOnClickListener {
                // Calling an update patient method and passing all edit text values.
                dbHandler.updatePatient(
                    patientFirstName,
                    patientFirstNameEdt.text.toString(),
                    patientMiddleNameEdt.text.toString(),
                    patientLastNameEdt.text.toString(),
                    patientIdNumberEdt.text.toString(),
                    patientTelephoneEdt.text.toString(),
                    patientTemperatureEdt.text.toString(),
                    patientHeightEdt.text.toString(),
                    patientWeightEdt.text.toString()
                )

                // Displaying a toast message that the patient has been updated.
                Toast.makeText(this@UpdatePatientActivity, "Patient Updated Successfully..", Toast.LENGTH_SHORT).show()

                // Launching the main activity.
                val intent = Intent(this@UpdatePatientActivity, Dashboard::class.java)
                startActivity(intent)
            }

            // Add onClick listener for delete button to delete the patient.
            deletePatientBtn.setOnClickListener {
                // Calling a method to delete the patient.
                dbHandler.deletePatient(patientFirstName)
                Toast.makeText(this@UpdatePatientActivity, "Deleted the patient successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UpdatePatientActivity, Dashboard::class.java)
                startActivity(intent)
            }

        }
    }
