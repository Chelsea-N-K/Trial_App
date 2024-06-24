package com.example.trial_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProvidersRegistration : Fragment() {

    // Creating variables for the edit texts, button and db handler
    private lateinit var providerFirstNameEdt: EditText
    private lateinit var providerLastNameEdt: EditText
    private lateinit var providerIdNumberEdt: EditText
    private lateinit var providerTelephoneEdt: EditText
    private lateinit var providerEmailEdt: EditText
    private lateinit var providerPasswordEdt: EditText
    private lateinit var facilityNameEdt: EditText
    private lateinit var newProviderBtn: Button
    private lateinit var dbHandler: DBHandler

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting on click listener for the register providers button.
        newProviderBtn.setOnClickListener {

            // Validating if the text fields are empty or not.

    }
*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.provider_registration, container, false)

        // Initializing all variables.
        providerFirstNameEdt = view.findViewById(R.id.providerFirstName)
        providerLastNameEdt = view.findViewById(R.id.providerLastName)
        providerIdNumberEdt = view.findViewById(R.id.providerIdNumber)
        providerTelephoneEdt = view.findViewById(R.id.providerTelephone)
        providerEmailEdt = view.findViewById(R.id.providerEmail)
        providerPasswordEdt = view.findViewById(R.id.providerPassword)
        facilityNameEdt = view.findViewById(R.id.facilityName)
        newProviderBtn = view.findViewById(R.id.registerButton)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Creating a new db handler class and passing the context to it.
        dbHandler = DBHandler(requireContext())
        newProviderBtn.apply {setOnClickListener {
            validateData()
        }
        }
    }

    private fun validateData() {
        val providerFirstName = providerFirstNameEdt.text.toString()
        val providerLastName = providerLastNameEdt.text.toString()
        val providerIdNumber = providerIdNumberEdt.text.toString()
        val providerTelephone = providerTelephoneEdt.text.toString()
        val providerEmail = providerEmailEdt.text.toString()
        val providerPassword = providerPasswordEdt.text.toString()
        val facilityName = facilityNameEdt.text.toString()

        if (providerFirstName.isEmpty() || providerLastName.isEmpty() || providerIdNumber.isEmpty() ||
            providerTelephone.isEmpty() || providerEmail.isEmpty() || providerPassword.isEmpty() ||
            facilityName.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter all the data", Toast.LENGTH_SHORT).show()
            return
        }



        // Calling a method to add new providers to sqlite database and passing all the values to it.
       // dbHandler.addNewProvider(providerFirstName, providerLastName, providerIdNumber, providerTelephone,
         //   providerEmail, providerPassword, facilityName)

        // Displaying a toast message after adding the data.
        Toast.makeText(requireContext(), "Provider has been added.", Toast.LENGTH_LONG).show()


        // Clearing all edit text fields.
        providerFirstNameEdt.setText("")
        providerLastNameEdt.setText("")
        providerIdNumberEdt.setText("")
        providerTelephoneEdt.setText("")
        providerEmailEdt.setText("")
        providerPasswordEdt.setText("")
        facilityNameEdt.setText("")
        return
    }
}
