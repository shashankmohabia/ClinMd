package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import org.jetbrains.anko.alert

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */

fun Context.showAppointmentRequestSentAlert(date: String, time: String) {
    val msg = "An appointment on $date at $time has been requested.\n\n You can see your confirmed appointments in your reminders"
    alert(msg) {
        title = "Appointment request sent"
        positiveButton("ok"){}
    }.show()
}

fun Context.showProgressDialog(title: String, msg: String, duration: Int) {

    val progress = ProgressDialog(this)
    progress.setTitle(title)
    progress.setMessage(msg)
    progress.show()

    val progressRunnable = Runnable { progress.cancel() }

    val pdCanceller = Handler()
    pdCanceller.postDelayed(progressRunnable, duration.toLong())

}