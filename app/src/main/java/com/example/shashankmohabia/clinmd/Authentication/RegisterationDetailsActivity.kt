package com.example.shashankmohabia.clinmd.Authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.Data.ServerClasses.ChildCount.countChild
import com.example.shashankmohabia.clinmd.Data.ServerClasses.CreateUser.createUserDatabase
import com.example.shashankmohabia.clinmd.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.registeration_details_activity.*

class RegisterationDetailsActivity : AppCompatActivity() {

    val dbref = FirebaseDatabase.getInstance().reference.child("Patients").child("Patient Id")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registeration_details_activity)

        countChild(dbref)

        register_button.setOnClickListener {
            if (first_name_registration.text.isNotEmpty() and last_name_registration.text.isNotEmpty() and age_registration.text.isNotEmpty()) {
                createUserDatabase(dbref)
            } else {
                test_registration.text = "Fill all the details"
            }

        }

    }
}
