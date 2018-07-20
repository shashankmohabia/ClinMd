package com.example.shashankmohabia.clinmd.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.Authentication.PinActivity
import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor
import com.example.shashankmohabia.clinmd.Data.LocalDb.DbFunctions
import com.example.shashankmohabia.clinmd.Data.ServerClasses.LoadDoctorData
import com.example.shashankmohabia.clinmd.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.lang.Thread.sleep

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class BootActivity : AppCompatActivity() {

    val Auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)
        LoadDoctorData().loadDoctorList(this@BootActivity, "ClinMd1")

        val intent = if (Auth.currentUser != null) {
            Intent(this, PinActivity::class.java)
                    .putExtra("type", "Enter")
        } else {
            Intent(this, InformationActivity::class.java)
        }

        Thread {
            try {
                sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                startActivity(intent)
                finish()
            }
        }.start()
    }
}

