package com.example.trial_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
            private const val DATABASE_NAME = "providersdb"
            private const val DATABASE_VERSION = 1
            const val TABLE_NAME = "Providers"
            const val COLUMN_ID = "id"
            const val COLUMN_FIRST_NAME = "first_name"
            const val COLUMN_LAST_NAME = "last_name"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_TELEPHONE = "telephone"
            const val COLUMN_PASSWORD = "password"
            const val COLUMN_ROLE = "role"
    }

        override fun onCreate(db: SQLiteDatabase?) {
            val createTable = "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_FIRST_NAME TEXT, " +
                    "$COLUMN_LAST_NAME TEXT, " +
                    "$COLUMN_EMAIL TEXT, " +
                    "$COLUMN_TELEPHONE TEXT, " +
                    "$COLUMN_PASSWORD TEXT, " +
                    "$COLUMN_ROLE TEXT)"
            db?.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

        fun registerProvider(firstName: String, lastName: String, email: String, telephone: String, password: String,
                         role: String): Long {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COLUMN_FIRST_NAME, firstName)
            contentValues.put(COLUMN_LAST_NAME, lastName)
            contentValues.put(COLUMN_EMAIL, email)
            contentValues.put(COLUMN_TELEPHONE, telephone)
            contentValues.put(COLUMN_PASSWORD, password)
            contentValues.put(COLUMN_ROLE, role)
            val result = db.insert(TABLE_NAME, null, contentValues)
            db.close()
            return result
        }

        fun authenticateUser(email: String, password: String): Boolean {
            val db = this.readableDatabase
            val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_ID), "$COLUMN_EMAIL=? AND $COLUMN_PASSWORD=?",
                arrayOf(email, password), null, null, null)
            val result = cursor.count > 0
            cursor.close()
            db.close()
            return result
        }
    }

