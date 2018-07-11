package com.example.shashankmohabia.clinmd.Authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.Data.ServerClasses.ChildCount
import com.example.shashankmohabia.clinmd.Data.ServerClasses.ChildCount.countChild
import com.example.shashankmohabia.clinmd.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.registeration_details_activity.*
import org.jetbrains.anko.toast

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
    fun createUserDatabase(dbref: DatabaseReference) {
        val userID = "ClinMd" + (ChildCount.childCount + 1).toString()
        test_registration.text = userID
        val newdbref = dbref.child(userID)
        val patientInfo: MutableMap<String, Any> = mutableMapOf()
        patientInfo["ID"] = userID
        patientInfo["firstName"] = first_name_registration.text.toString()
        patientInfo["lastName"] = last_name_registration.text.toString()
        patientInfo["mobile"] = intent.extras.get("phone")
        patientInfo["gender"] = "Male"
        patientInfo["age"] = age_registration.text.toString()
        newdbref.updateChildren(patientInfo)
        toast("$userID added")
        startActivity(Intent(this, PinActivity::class.java).putExtra("type", "Set"))
    }
}
