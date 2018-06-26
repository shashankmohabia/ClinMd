package com.example.shashankmohabia.clinmd.Authentication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color

/**
 * Created by Shashank Mohabia on 6/26/2018.
 */
class Prefs (context: Context) {

    val PREFS_FILENAME = "pinFile"
    val pinCode = "pin"
    val defaultPin = "4321"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var pin: String
        get() = prefs.getString(pinCode, defaultPin)
        set(value) = prefs.edit().putString(pinCode, value).apply()
}