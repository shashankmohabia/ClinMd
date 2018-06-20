package com.example.shashankmohabia.clinmd.Authentication

import android.content.Context
import com.example.shashankmohabia.clinmd.UI.RegisterActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Shashank Mohabia on 6/20/2018.
 */
 class Register{

    private val Auth = FirebaseAuth.getInstance()

    fun register(context: Context, email:String, password:String){
            Auth.signInWithEmailAndPassword(email, password)
    }
}