package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.clinmd.R
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by Shashank Mohabia on 7/19/2018.
 */


fun AppCompatActivity.startFragmentTransaction(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
}

fun AppCompatActivity.getGalleryIntent(requestCode: Int) {
    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "image/*"
    }
    startActivityForResult(intent, 1)
}

fun AppCompatActivity.getCameraIntent(requestCode: Int) {
    val intent = Intent("android.media.action.IMAGE_CAPTURE")
    startActivityForResult(intent, requestCode)
}

fun Context.getDatePickerIntent() {
    val calender = Calendar.getInstance()
    DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val date = getDate(year, monthOfYear, dayOfMonth)
                this.getTimePickerIntent(date)
            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
    ).apply {
        setButton(DatePickerDialog.BUTTON_POSITIVE, "Continue", this)
        setTitle("Choose date")
        show()
    }
}

fun Context.getTimePickerIntent(date: String) {
    val calender = Calendar.getInstance()
    val currentHour = calender.get(Calendar.HOUR_OF_DAY)
    val currentMinute = calender.get(Calendar.MINUTE)
    TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                val time = getTime(selectedHour, selectedMinute)
                showAppointmentRequestSentAlert(date, time)
            },
            currentHour,
            currentMinute,
            false
    ).apply {
        setButton(TimePickerDialog.BUTTON_POSITIVE, "request appointment", this)
        setButton(TimePickerDialog.BUTTON_NEGATIVE, "Change Date", this)
        setTitle("Choose Time")
        setOnCancelListener { getDatePickerIntent() }
        show()
    }
}


fun Context.getDialerIntent(number: String) {
    makeCall(number)
}

fun Context.getMapIntent(address: String) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.`package` = "com.google.android.apps.maps"
    startActivity(mapIntent)
}

fun Context.getShareIntent() {
    val intent = Intent(android.content.Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
    intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
    startActivity(Intent.createChooser(intent, "Share via"))
}

fun Context.getWhatsAppIntent(number: String) {
    try {
        val sendIntent = Intent("android.intent.action.MAIN")
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Doctor")
        sendIntent.putExtra("jid", "$number@s.whatsapp.net")
        sendIntent.`package` = "com.whatsapp"
        startActivity(sendIntent)
    } catch (e: Exception) {
        toast(e.toString())
    }
}

fun Context.doesContactExists(number: String): Boolean {
    /// number is the phone number
    val lookupUri = Uri.withAppendedPath(
            ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
            Uri.encode(number))
    val mPhoneNumberProjection = arrayOf(ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME)
    val cur = contentResolver.query(lookupUri, mPhoneNumberProjection, null, null, null)
    cur.use { cur ->
        if (cur!!.moveToFirst()) {
            return true
        }
    }
    return false
}

fun Context.getSaveContactIntent(name: String, number: String, email: String) {
    val intent = Intent(Intent.ACTION_INSERT)
    intent.type = ContactsContract.Contacts.CONTENT_TYPE

    intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
    intent.putExtra(ContactsContract.Intents.Insert.PHONE, number)
    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email)

    startActivity(intent)
}