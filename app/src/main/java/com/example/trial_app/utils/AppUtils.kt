package com.example.trial_app.utils

class AppUtils {
    fun encryptPassword(password: String) : String{
        //TO DO
        return password
    }


    // Function to calculate BMI
    fun calculateBMI(weight: Double, height: Double): Double {
        val heightMeters = height/100
        return weight / (heightMeters * heightMeters)
    }
}