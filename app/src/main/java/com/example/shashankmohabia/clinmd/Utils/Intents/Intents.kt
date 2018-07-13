package com.example.shashankmohabia.clinmd.Utils.Intents

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import org.jetbrains.anko.toast
import android.support.v4.content.ContextCompat.startActivity
import android.content.ContentUris
import android.provider.CalendarContract
import android.R.attr.startYear
import com.example.shashankmohabia.clinmd.Core.Main.MainActivity
import android.app.DatePickerDialog
import android.app.FragmentTransaction
import android.widget.DatePicker
import com.example.shashankmohabia.clinmd.Utils.FragmentListeners.FragmentListeners
import java.util.*


/**
 * Created by Shashank Mohabia on 7/12/2018.
 */
object Intents : Activity() {

    /*fun getGalleryIntent(context: Context){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image*//*"
        startActivityForResult(intent, 1)
    }*/

    /*fun startFragmentTransaction(context: Context,fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit()
    }*/

    /*fun getDatePickerIntent(context: Context) {
        val calender = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->
                    toast("year = $year, month = $monthOfYear, day = $dayOfMonth")
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }*/

    fun getDialerIntent(context: Context, number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:8504939946")
        context.startActivity(intent)
    }

    fun getMapIntent(context: Context, address: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.`package` = "com.google.android.apps.maps"
        context.startActivity(mapIntent)
    }

    fun getShareIntent(context: Context) {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun getWhatsAppIntent(context: Context, number: String) {
        try {
            val sendIntent = Intent("android.intent.action.MAIN")
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Doctor")
            sendIntent.putExtra("jid", "$number@s.whatsapp.net")
            sendIntent.`package` = "com.whatsapp"
            context.startActivity(sendIntent)
        } catch (e: Exception) {
            toast(e.toString())
        }
    }

    fun doesContactExists(context: Context, number: String): Boolean {
        /// number is the phone number
        val lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number))
        val mPhoneNumberProjection = arrayOf(ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME)
        val cur = context.contentResolver.query(lookupUri, mPhoneNumberProjection, null, null, null)
        cur.use { cur ->
            if (cur!!.moveToFirst()) {
                return true
            }
        }
        return false
    }

    fun getSaveContactIntent(context: Context, name: String, number: String, email: String) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE

        intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, number)
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email)

        context.startActivity(intent)
    }
}