package com.example.trial_app.dashboard.ui.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trial_app.DBHandler
import com.example.trial_app.R
import com.example.trial_app.R.layout


class GalleryFragment : Fragment() {

    /*private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    } */

    // Creating variables for the edit texts, button and db handler
    private lateinit var patientFirstNameEdt: EditText
    private lateinit var patientMiddleNameEdt: EditText
    private lateinit var patientLastNameEdt: EditText
    private lateinit var patientIdNumberEdt: EditText
    private lateinit var patientTelephoneEdt: EditText
    private lateinit var patientTemperatureEdt: EditText
    private lateinit var patientHeightEdt: EditText
    private lateinit var patientWeightEdt: EditText
    private lateinit var newPatientBtn: Button
    private lateinit var viewPatientBtn: Button
    private lateinit var dbHandler: DBHandler

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(layout.fragment_gallery, container, false)

        // Initializing all variables.
        patientFirstNameEdt = view.findViewById(R.id.patientFirstName)
        patientMiddleNameEdt = view.findViewById(R.id.patientMiddleName)
        patientLastNameEdt = view.findViewById(R.id.patientLastName)
        patientIdNumberEdt = view.findViewById(R.id.patientIdNumber)
        patientTelephoneEdt = view.findViewById(R.id.patientTelephone)
        patientTemperatureEdt = view.findViewById(R.id.patientTemperature)
        patientHeightEdt = view.findViewById(R.id.patientHeight)
        patientWeightEdt = view.findViewById(R.id.patientWeight)
        newPatientBtn = view.findViewById(R.id.newPatientButton)
        viewPatientBtn = view.findViewById(R.id.viewPatientButton)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Creating a new db handler class and passing the context to it.
        dbHandler = DBHandler(requireContext())
        newPatientBtn.apply {setOnClickListener {
            validateData()
        }
        }
    }

    private fun validateData() {
        val patientFirstName = patientFirstNameEdt.text.toString()
        val patientMiddleName = patientMiddleNameEdt.text.toString()
        val patientLastName = patientLastNameEdt.text.toString()
        val patientIdNumber = patientIdNumberEdt.text.toString()
        val patientTelephone = patientTelephoneEdt.text.toString()
        val patientTemperature = patientTemperatureEdt.text.toString()
        val patientHeight = patientHeightEdt.text.toString()
        val patientWeight = patientWeightEdt.text.toString()

        if (patientFirstName.isEmpty() || patientMiddleName.isEmpty() || patientLastName.isEmpty() ||
            patientIdNumber.isEmpty() || patientTelephone.isEmpty() || patientTemperature.isEmpty() ||
            patientHeight.isEmpty() || patientWeight.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter all the data", Toast.LENGTH_SHORT).show()
            return
        }

        // Calling a method to add new patients to sqlite database and passing all the values to it.
        dbHandler.addNewPatient(patientFirstName, patientMiddleName, patientLastName, patientIdNumber,
            patientTelephone, patientTemperature, patientHeight, patientWeight)

        // Displaying a toast message after adding the data.
        Toast.makeText(requireContext(), "Patient has been added.", Toast.LENGTH_LONG).show()

        // Clearing all edit text fields.
        patientFirstNameEdt.setText("")
        patientMiddleNameEdt.setText("")
        patientLastNameEdt.setText("")
        patientIdNumberEdt.setText("")
        patientTelephoneEdt.setText("")
        patientTemperatureEdt.setText("")
        patientHeightEdt.setText("")
        patientWeightEdt.setText("")

        viewPatientBtn.setOnClickListener {
            // Opening a new activity via an intent.
           // val intent = Intent(requireContext(), PatientsDisplay::class.java)
           // startActivity(intent)
        }
    }
}