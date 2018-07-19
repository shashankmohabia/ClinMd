package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View

/**
 * Created by Shashank Mohabia on 7/19/2018.
 */

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

@RequiresApi(Build.VERSION_CODES.M)
fun View.getOutOfFocus() {
    this.foreground.alpha = 220
}

@RequiresApi(Build.VERSION_CODES.M)
fun View.getInFocus() {
    this.foreground.alpha = 0
}
