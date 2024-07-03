package com.example.trial_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_app.R
import com.example.trial_app.models.PatientModal


    class PatientRVAdapter(
        private val patientModalArrayList: ArrayList<PatientModal>,
        private val context: Context
    ) : RecyclerView.Adapter<PatientRVAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // Inflating our layout file for our recycler view items.
            val view = LayoutInflater.from(parent.context).inflate(R.layout.patients_display_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Setting data to our views of recycler view item.
            val modal = patientModalArrayList[position]
            holder.patientFirstNameTV.text = modal.patientFirstName
            holder.patientMiddleNameTV.text = modal.patientMiddleName
            holder.patientLastNameTV.text = modal.patientLastName
            holder.patientIdNumberTV.text = modal.patientIdNumber
            holder.patientTelephoneTV.text = modal.patientTelephone
            holder.patientTemperatureTV.text = modal.patientTemperature
            holder.patientHeightTV.text = modal.patientHeight
            holder.patientWeightTV.text = modal.patientWeight
        }

        override fun getItemCount(): Int {
            // Returning the size of our array list
            return patientModalArrayList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // Creating variables for our text views.
            val patientFirstNameTV: TextView = itemView.findViewById(R.id.patientFirstNameView)
            val patientMiddleNameTV: TextView = itemView.findViewById(R.id.patientMiddleNameView)
            val patientLastNameTV: TextView = itemView.findViewById(R.id.patientLastNameView)
            val patientIdNumberTV: TextView = itemView.findViewById(R.id.patientIdNumberView)
            val patientTelephoneTV: TextView = itemView.findViewById(R.id.patientTelephoneView)
            val patientTemperatureTV: TextView = itemView.findViewById(R.id.patientTemperatureView)
            val patientHeightTV: TextView = itemView.findViewById(R.id.patientHeightView)
            val patientWeightTV: TextView = itemView.findViewById(R.id.patientWeightView)

        }
    }

