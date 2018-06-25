package com.example.shashankmohabia.clinmd.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_registeration_details.*
import org.jetbrains.anko.toast

class RegisterationDetailsActivity : AppCompatActivity() {

    val dbref = FirebaseDatabase.getInstance().reference.child("Patients")
    var childCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration_details)

        countChild()

        register_button.setOnClickListener {
            createUserDatabase()
        }

    }

    private fun createUserDatabase() {
        if (first_name_registration.text.isNotEmpty() and last_name_registration.text.isNotEmpty() and age_registration.text.isNotEmpty()){
            val userID = "ClinMD" + (childCount+1).toString()
            test_registration.text = userID
            val newdbref = dbref.child(userID)
            val patientInfo: MutableMap<String, Any> = mutableMapOf()
            patientInfo.put("first_name", first_name_registration.text.toString())
            patientInfo.put("second_name", last_name_registration.text.toString())
            patientInfo.put("phone", intent.extras.get("phone"))
            patientInfo.put("age", age_registration.text.toString())
            newdbref.updateChildren(patientInfo)
            toast("$userID added")
        }
        else{
            test_registration.text = "Fill all the details"
        }

    }

    private fun countChild() {
        dbref.child("PatientID").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                childCount = dataSnapshot.childrenCount.toInt()
            }

        })
    }
}
