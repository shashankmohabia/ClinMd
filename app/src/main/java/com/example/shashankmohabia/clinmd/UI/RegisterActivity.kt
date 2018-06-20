package com.example.shashankmohabia.clinmd.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast

import com.example.shashankmohabia.clinmd.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val Auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            if (register_email.text.isNotBlank() and register_password.text.isNotBlank()) {
                Auth!!.createUserWithEmailAndPassword(register_email.text.toString(), register_password.text.toString()).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(this, "Sign Up Error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                })
            } else {
                Toast.makeText(this, "fill all the details", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
