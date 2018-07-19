package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.FrameLayout

/**
 * Created by Shashank Mohabia on 7/19/2018.
 */

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun FrameLayout.getOutOfFocus() {
    this.foreground.alpha = 220
}

fun FrameLayout.getInFocus() {
    this.foreground.alpha = 0
}
