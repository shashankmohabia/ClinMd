package com.example.shashankmohabia.clinmd.Utils.Extensions

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Shashank Mohabia on 7/13/2018.
 */


    fun getTime(hr: Int, min: Int): String {
        val tme = Time(hr, min, 0)//seconds by default set to zero
        val formatter = SimpleDateFormat("h:mm a")
        return formatter.format(tme)
    }

    fun getDate(year: Int, month: Int, date: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(year, month, date)
        }
        val format = SimpleDateFormat("MM-dd-yyyy")
        return format.format(calendar.time)
    }


