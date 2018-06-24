package com.example.shashankmohabia.clinmd.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.Authentication.LoginActivity
import com.example.shashankmohabia.clinmd.R
import java.lang.Thread.sleep

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val intent = Intent(this, LoginActivity::class.java)

        Thread{
            try{
                sleep(3000)
            }
            catch (e:InterruptedException){
                e.printStackTrace()
            }
            finally {
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
