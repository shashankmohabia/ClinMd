package com.example.shashankmohabia.clinmd.Authentication.Pin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.shashankmohabia.clinmd.R
import com.andrognito.pinlockview.PinLockListener
import com.example.shashankmohabia.clinmd.UI.MainActivity
import kotlinx.android.synthetic.main.activity_pin.*


class PinActivity : AppCompatActivity() {

    lateinit var type:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        type = intent.extras.get("type").toString()

        profile_name.text = type.toString() + " Pin"

        pin_lock_view.setPinLockListener(mPinLockListener)
        pin_lock_view.attachIndicatorDots(indicator_dots)
    }


    private val mPinLockListener = object : PinLockListener {
        override fun onComplete(pin: String) {
            Log.d("pin", "Pin complete: $pin")
            if(pin == "1234" && type == "Enter"){
                val intent = Intent(this@PinActivity, this@PinActivity::class.java)
                intent.putExtra("type", "Confirm")
                startActivity(intent)
                finish()
            }

        }

        override fun onEmpty() {
            Log.d("pin", "Pin empty")
        }

        override fun onPinChange(pinLength: Int, intermediatePin: String) {
            Log.d("pin", "Pin changed, new length $pinLength with intermediate pin $intermediatePin")
        }
    }
}