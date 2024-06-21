package com.example.trial_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        // Database Name
        private const val DB_NAME = "healthcaredb"

        // Database Version
        private const val DB_VERSION = 1

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
    }
}