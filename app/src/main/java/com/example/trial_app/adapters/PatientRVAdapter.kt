package com.example.trial_app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_app.R
import com.example.trial_app.UpdatePatientActivity
import com.example.trial_app.modals.PatientModal


    class PatientRVAdapter(
        private val patientModalArrayList: ArrayList<PatientModal>,
        private val context: Context
    ) : RecyclerView.Adapter<PatientRVAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // Inflate the layout file for the recycler view items.
            val view = LayoutInflater.from(parent.context).inflate(R.layout.patients_display_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Set data to the views of recycler view item.
            val modal = patientModalArrayList[position]
            holder.patientFirstNameTV.text = modal.patientFirstName
            holder.patientMiddleNameTV.text = modal.patientMiddleName
            holder.patientLastNameTV.text = modal.patientLastName
            holder.patientIdNumberTV.text = modal.patientIdNumber
            holder.patientTelephoneTV.text = modal.patientTelephone
            holder.patientTemperatureTV.text = modal.patientTemperature
            holder.patientHeightTV.text = modal.patientHeight
            holder.patientWeightTV.text = modal.patientWeight

            // Add an onClick listener for the recycler view item.
            holder.itemView.setOnClickListener {
                // Call an intent.
                val intent = Intent(context, UpdatePatientActivity::class.java)

                // Pass all the values.
                intent.putExtra("first_name", modal.patientFirstName)
                intent.putExtra("middle_name", modal.patientMiddleName)
                intent.putExtra("last_name", modal.patientLastName)
                intent.putExtra("id_number", modal.patientIdNumber)
                intent.putExtra("telephone", modal.patientTelephone)
                intent.putExtra("temperature", modal.patientTemperature)
                intent.putExtra("height", modal.patientHeight)
                intent.putExtra("weight", modal.patientWeight)
                // Start the activity.
                context.startActivity(intent)
            }

        }

        override fun getItemCount(): Int {
            // Return the size of our array list
            return patientModalArrayList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // Create variables for our text views.
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

