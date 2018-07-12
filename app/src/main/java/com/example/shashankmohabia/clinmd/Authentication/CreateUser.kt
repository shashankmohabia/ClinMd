package com.example.shashankmohabia.clinmd.Authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.shashankmohabia.clinmd.Data.ServerClasses.ChildCount
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.registeration_details_activity.*
import org.jetbrains.anko.toast

/**
 * Created by Shashank Mohabia on 7/10/2018.
 */
object CreateUser :
        Activity() {
/*
    fun createUserDatabase(context: Context, data: DatabaseReference) {
        val userID = "ClinMd" + (ChildCount.childCount + 1).toString()
        test_registration.text = userID
        val newdbref = data.child(userID)
        val patientInfo: MutableMap<String, Any> = mutableMapOf()
        patientInfo["ID"] = userID
        patientInfo["firstName"] = first_name_registration.text.toString()
        patientInfo["lastName"] = last_name_registration.text.toString()
        patientInfo["mobile"] = intent.extras.get("phone")
        patientInfo["gender"] = "Male"
        patientInfo["age"] = age_registration.text.toString()
        newdbref.updateChildren(patientInfo)
        toast("$userID added")
        context.startActivity(Intent(this, PinActivity::class.java).putExtra("type", "Set"))
    }*/
}