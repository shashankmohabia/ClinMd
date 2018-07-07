package com.example.shashankmohabia.clinmd.Authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.shashankmohabia.clinmd.R
import com.andrognito.pinlockview.PinLockListener
import com.example.shashankmohabia.clinmd.Core.Main.MainActivity
import com.example.shashankmohabia.clinmd.Data.SharedPreferences.PinPrefs
import kotlinx.android.synthetic.main.activity_pin.*


class PinActivity : AppCompatActivity() {

    lateinit var type: String
    lateinit var pinPrefs: PinPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        type = intent.extras.get("type").toString()
        pinPrefs = PinPrefs(this)

        profile_name.text = type.toString() + " Pin"

        if (intent.extras.get("error") != null) {
            pinError.text = intent.extras.get("error").toString()
        }

        if(type == "Enter"){
            forgotPin.isClickable =true
            forgotPin.visibility = View.VISIBLE
        }

        pin_lock_view.setPinLockListener(mPinLockListener)
        pin_lock_view.attachIndicatorDots(indicator_dots)
    }


    private val mPinLockListener = object : PinLockListener {
        override fun onComplete(pin: String) {
            Log.d("patient", "Pin complete: $pin")
            when (type) {
                "Enter" -> {
                    if (pin == pinPrefs.pin) {
                        startActivity(Intent(this@PinActivity, MainActivity::class.java))
                        finish()
                    }
                    else{
                        startActivity(
                                Intent(this@PinActivity, this@PinActivity::class.java)
                                        .putExtra("type", "Enter")
                                        .putExtra("error", "Incorrect Pin")
                        )
                        finish()
                    }
                }

                "Set" -> {
                    startActivity(
                            Intent(this@PinActivity, this@PinActivity::class.java)
                                    .putExtra("type", "Confirm")
                                    .putExtra("lastPin", pin)
                    )
                    finish()
                }

                "Confirm" -> {
                    val lastpin = intent.extras.get("lastPin").toString()
                    if (pin == lastpin) {
                        pinPrefs.pin = pin
                        startActivity(
                                Intent(this@PinActivity, MainActivity::class.java)
                        )
                        finish()
                    } else {
                        startActivity(
                                Intent(this@PinActivity, this@PinActivity::class.java)
                                        .putExtra("type", "Set")
                                        .putExtra("error", "Pins do not match")
                        )
                        finish()
                    }
                }
            }


        }

        override fun onEmpty() {
            Log.d("patient", "Pin empty")
        }

        override fun onPinChange(pinLength: Int, intermediatePin: String) {
            Log.d("patient", "Pin changed, new length $pinLength with intermediate patient $intermediatePin")
        }
    }
}