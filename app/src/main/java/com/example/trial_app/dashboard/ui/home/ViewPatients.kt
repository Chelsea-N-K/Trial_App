package com.example.trial_app.dashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_app.DBHandler
import com.example.trial_app.adapters.PatientRVAdapter
import com.example.trial_app.databinding.ViewPatientBinding
import com.example.trial_app.modals.PatientModal

class ViewPatients : Fragment() {

    // dbHandler, adapter, and recycler view.
    private lateinit var patientModalArrayList: ArrayList<PatientModal>
    private lateinit var dbHandler: DBHandler
    private lateinit var patientRVAdapter: PatientRVAdapter
    private lateinit var patientsRV: RecyclerView
    private lateinit var binding: ViewPatientBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = ViewPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing all variables.
        patientModalArrayList = ArrayList()
        dbHandler = DBHandler(requireContext())

        // Getting our course array list from db handler class.
        patientModalArrayList = dbHandler.readPatients()

        // Passing our array list to our adapter class.
        patientRVAdapter = PatientRVAdapter(patientModalArrayList, requireContext())
        //val view = inflater.inflate(R.layout.view_patient, container, false)
        patientsRV = binding.recyclerView

        // Setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        patientsRV.layoutManager = linearLayoutManager

        // Setting our adapter to recycler view.
        patientsRV.adapter = patientRVAdapter
    }
}



