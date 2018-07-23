package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.text.format.DateFormat
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

val String.toDate: CharSequence?
    get() {
        /*val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = this.toLong() * 1000L
        return DateFormat.format("dd-MM-yyyy", cal).toString()*/

        val timestamp = java.lang.Long.parseLong(this) * 1000L
        val sdf = SimpleDateFormat("MMM dd yyyy")
        val netDate = Date(timestamp)
        return sdf.format(netDate)
    }

