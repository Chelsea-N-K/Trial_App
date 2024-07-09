package com.example.trial_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trial_app.modals.PatientModal

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        // Database Name
        private const val DB_NAME = "patientsdb"

        // Database Version
        private const val DB_VERSION = 1

        /*
        // Table Name
        private const val TABLE_NAME = "providers"

        // Column Names
        private const val ID_COL = "Provider ID"
        private const val FIRST_NAME_COL = "First Name"
        private const val LAST_NAME_COL = "Last Name"
        private const val PASSPORT_COL = "National ID Number/Passport Number"
        private const val TELEPHONE_COL = "Telephone Number"
        private const val EMAIL_COL = "Email Address"
        private const val PASSWORD_COL = "Password"
        private const val FACILITY_COL = "Facility Name"

    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create table SQL query
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + PASSPORT_COL + " TEXT,"
                + TELEPHONE_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + FACILITY_COL + " TEXT)")
        db.execSQL(query) //Executes above query
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if it existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        // Create tables again
        onCreate(db)
    }

    // Method to add new healthcare provider to the SQLite database
    fun addNewProvider(providerFirstName: String, providerLastName: String, providerIdNumber: String,
                       providerTelephone: String, providerEmail: String, providerPassword: String,
                       facilityName: String) {
        // Get writable database
        val db = this.writableDatabase

        // Create content values
        val values = ContentValues()

        // Put values with key-value pairs
        values.put(FIRST_NAME_COL, providerFirstName)
        values.put(LAST_NAME_COL, providerLastName)
        values.put(PASSPORT_COL, providerIdNumber)
        values.put(TELEPHONE_COL, providerTelephone)
        values.put(EMAIL_COL, providerEmail)
        values.put(PASSWORD_COL, providerPassword)
        values.put(FACILITY_COL, facilityName)

        // Insert values into the table
        db.insert(TABLE_NAME, null, values)

        // Close the database
        db.close()
    }*/
        // Table Name
        private const val TABLE_NAME = "patients"

        // Column Names
        private const val ID_COL = "Patient_ID"
        private const val FIRST_NAME_COL = "First_Name"
        private const val MIDDLE_NAME_COL = "Middle_Name"
        private const val LAST_NAME_COL = "Last_Name"
        private const val PASSPORT_COL = "National_ID_Number"
        private const val TELEPHONE_COL = "Telephone_Number"
        private const val TEMPERATURE_COL = "Body_Temperature"
        private const val HEIGHT_COL = "Height"
        private const val WEIGHT_COL = "Weight"

    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create table - SQL query
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + MIDDLE_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + PASSPORT_COL + " TEXT,"
                + TELEPHONE_COL + " TEXT,"
                + TEMPERATURE_COL + " DOUBLE,"
                + HEIGHT_COL + " DOUBLE,"
                + WEIGHT_COL + " DOUBLE)")
        db.execSQL(query) //Executes above query
    }


    // Method to add new patient to the SQLite database
    fun addNewPatient(
        patientFirstName: String, patientMiddleName: String, patientLastName: String, patientIdNumber: String,
        patientTelephone: String, patientTemperature: String, patientHeight: String,
        patientWeight: String
    ) {
        // Get writable database
        val db = this.writableDatabase

        // Create content values
        val values = ContentValues()

        // Put values with key-value pairs
        values.put(FIRST_NAME_COL, patientFirstName)
        values.put(MIDDLE_NAME_COL, patientMiddleName)
        values.put(LAST_NAME_COL, patientLastName)
        values.put(PASSPORT_COL, patientIdNumber)
        values.put(TELEPHONE_COL, patientTelephone)
        values.put(TEMPERATURE_COL, patientTemperature)
        values.put(HEIGHT_COL, patientHeight)
        values.put(WEIGHT_COL, patientWeight)

        // Insert values into the table
        db.insert(TABLE_NAME, null, values)

        // Close the database
        db.close()
    }

    // Create a new method for reading all the patients.
    fun readPatients(): ArrayList<PatientModal> {
        // Creating a database for reading the database.
        val db = this.readableDatabase

        // Create a cursor with query to read data from database.
        val cursorPatients = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // Create a new array list.
        val patientModalArrayList = ArrayList<PatientModal>()

        // Move the cursor to first position.
        if (cursorPatients.moveToFirst()) {
            do {
                // Add the data from cursor to our array list.
                patientModalArrayList.add(
                    PatientModal(
                        cursorPatients.getString(1),
                        cursorPatients.getString(2),
                        cursorPatients.getString(3),
                        cursorPatients.getString(4),
                        cursorPatients.getString(5),
                        cursorPatients.getString(6),
                        cursorPatients.getString(7),
                        cursorPatients.getString(8)
                    )
                )
            } while (cursorPatients.moveToNext())
            // Moving the cursor to next.
        }

        // Closing the cursor and return the array list.
        cursorPatients.close()
        return patientModalArrayList
    }

    fun updatePatient(originalPatientName: String, patientFirstName: String, patientMiddleName: String,
                      patientLastName: String, patientIdNumber: String, patientTelephone: String,
                      patientTemperature: String, patientHeight: String, patientWeight: String) {

        // Calling a method to get writable database.
        val db = this.writableDatabase
        val values = ContentValues()

        // Pass all values along with its key and value pair.
        values.put(FIRST_NAME_COL, patientFirstName)
        values.put(MIDDLE_NAME_COL, patientMiddleName)
        values.put(LAST_NAME_COL, patientLastName)
        values.put(PASSPORT_COL, patientIdNumber)
        values.put(TELEPHONE_COL, patientTelephone)
        values.put(TEMPERATURE_COL, patientTemperature)
        values.put(HEIGHT_COL, patientHeight)
        values.put(WEIGHT_COL, patientWeight)

        // Call an update method to update the database and pass the values.
        // Compare it with the name of the patient which is stored in the original name variable.
        db.update(TABLE_NAME, values, "name=?", arrayOf(originalPatientName))
        db.close()
    }

    fun deletePatient(patientFirstName: String) {
        // Creating a variable to write to our database.
        val db = this.writableDatabase

        // Calling a method to delete the patient and comparing it with the patient's first name.
        db.delete(TABLE_NAME, "name=?", arrayOf(patientFirstName))
        db.close()
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if it existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        // Create tables again
        onCreate(db)
    }
}



