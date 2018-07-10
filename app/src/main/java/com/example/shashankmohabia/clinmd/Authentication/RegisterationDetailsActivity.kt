package com.example.shashankmohabia.clinmd.Authentication
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.registeration_details_activity.*
import org.jetbrains.anko.toast

class RegisterationDetailsActivity : AppCompatActivity() {

    val dbref = FirebaseDatabase.getInstance().reference.child("Patients").child("Patient Id")
    var childCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registeration_details_activity)

        countChild()

        register_button.setOnClickListener {
            createUserDatabase()
        }

    }

    private fun createUserDatabase() {
        if (first_name_registration.text.isNotEmpty() and last_name_registration.text.isNotEmpty() and age_registration.text.isNotEmpty()){
            val userID = "ClinMd" + (childCount+1).toString()
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
        else{
            test_registration.text = "Fill all the details"
        }

    }

    private fun countChild() {
        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                childCount = dataSnapshot.childrenCount.toInt()
            }

        })
    }
}
