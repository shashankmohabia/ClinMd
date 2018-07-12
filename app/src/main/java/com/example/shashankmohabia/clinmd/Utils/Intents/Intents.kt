package com.example.shashankmohabia.clinmd.Utils.Intents

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import com.example.shashankmohabia.clinmd.R
import org.jetbrains.anko.toast

/**
 * Created by Shashank Mohabia on 7/12/2018.
 */
object Intents:Activity() {

    fun getShareIntent(context: Context) {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun getWhatsappIntent(context: Context, number: String) {
        val smsNumber = number //without '+'
        try {
            val sendIntent = Intent("android.intent.action.MAIN")
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Doctor")
            sendIntent.putExtra("jid", "$smsNumber@s.whatsapp.net")
            sendIntent.`package` = "com.whatsapp"
            context.startActivity(sendIntent)
        } catch (e: Exception) {
            toast(e.toString())
        }
    }

    fun doescontactExists(context: Context, number: String): Boolean {
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