package com.example.shashankmohabia.clinmd.Utils.Intents

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import com.example.shashankmohabia.clinmd.R
import org.jetbrains.anko.toast

/**
 * Created by Shashank Mohabia on 7/12/2018.
 */
object Intents:Activity() {

    /*fun startFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit()
    }*/

    /*fun getWhatsappIntent(number:String){
        val smsNumber = number //without '+'
        try {
            val sendIntent = Intent("android.intent.action.MAIN")
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Doctor")
            sendIntent.putExtra("jid", "$smsNumber@s.whatsapp.net")
            sendIntent.`package` = "com.whatsapp"
            startActivity(sendIntent)
        } catch (e: Exception) {
            toast(e.toString())
        }
    }

    private fun getShareIntent() {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
        startActivity(Intent.createChooser(intent, "Share via"))
    }*/
}