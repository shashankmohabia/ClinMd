package com.example.shashankmohabia.clinmd.Data.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Shashank Mohabia on 6/26/2018.
 */
class PinPrefs (context: Context) {

    val PREFS_FILENAME = "pinFile"
    val pinCode = "patient"
    val defaultPin = "4321"
    val pinPrefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var pin: String
        get() = pinPrefs.getString(pinCode, defaultPin)
        set(value) = pinPrefs.edit().putString(pinCode, value).apply()
}