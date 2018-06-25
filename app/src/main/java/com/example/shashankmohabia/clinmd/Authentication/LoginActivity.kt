package com.example.shashankmohabia.clinmd.Authentication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import com.firebase.ui.auth.ErrorCodes
import android.app.Activity
import android.util.Log
import com.example.shashankmohabia.clinmd.UI.*
import com.firebase.ui.auth.IdpResponse
import org.jetbrains.anko.toast
import com.google.firebase.auth.FirebaseUserMetadata


class LoginActivity : AppCompatActivity() {

    val Auth = FirebaseAuth.getInstance()
    private val LOGIN_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Auth.currentUser != null) {
            //Already Logged In condition
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    AuthUI.IdpConfig.PhoneBuilder().build()))
                            .build(),
                    LOGIN_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
                val metadata = Auth.currentUser?.metadata
                if (metadata != null) {
                    if (metadata.creationTimestamp == metadata.lastSignInTimestamp) {
                        // The user is new, show them a fancy intro screen!
                        val intent = Intent(this, RegisterationDetailsActivity::class.java)
                        intent.putExtra("phone", Auth.currentUser?.phoneNumber)
                        startActivity(intent)
                        finish()
                    } else {
                        // This is an existing user, show them a welcome back screen.
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }

            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    //showSnackbar(R.string.sign_in_cancelled)
                    toast("Sign in cancelled")
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    //showSnackbar(R.string.no_internet_connection)
                    toast("No internet")
                    return
                }

                //showSnackbar(R.string.unknown_error)
                toast("Unknown Error")
                Log.d("shashank", response.error.toString())
                return
            }
        }
    }
}


