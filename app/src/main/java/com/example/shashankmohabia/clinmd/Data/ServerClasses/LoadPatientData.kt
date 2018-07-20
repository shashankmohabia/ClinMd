package com.example.shashankmohabia.clinmd.Data.ServerClasses

import com.example.shashankmohabia.clinmd.Data.DomainModals.Patient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.toast
import java.util.ArrayList
import android.content.Context


class LoadPatientData {

    fun loadPatientDetails(context: Context) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Patients").child("Patient Id")
        lateinit var phone: String
        val familyList = ArrayList<Patient>()
        if (FirebaseAuth.getInstance().currentUser != null) {
            phone = FirebaseAuth.getInstance().currentUser?.phoneNumber.toString()
            context.toast(phone)
            dbRef.orderByChild("mobile").equalTo(phone).addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                    val map = dataSnapshot.value as Map<*, *>?
                    familyList.add(
                            Patient("ClinMd1",
                                    map!!["first_name"].toString(),
                                    map["last_name"].toString(),
                                    map["phone"].toString(),
                                    map["age"].toString()
                            )
                    )
                    context.toast("size = " + familyList.size)
                    context.toast(dataSnapshot.value.toString())
                }

            })
        } else {
            context.toast("Problem Loading Data")
        }
    }
}