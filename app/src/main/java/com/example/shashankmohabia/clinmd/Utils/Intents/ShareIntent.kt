package com.example.shashankmohabia.clinmd.Utils.Intents

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity

/**
 * Created by Shashank Mohabia on 7/7/2018.
 */

fun getShareIntent(context: Context?, type: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
    intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
    startActivity(context.Intent.createChooser(intent, "Share via"))
}