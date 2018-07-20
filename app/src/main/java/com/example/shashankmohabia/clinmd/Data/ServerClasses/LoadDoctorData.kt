package com.example.shashankmohabia.clinmd.Data.ServerClasses

import android.content.Context
import com.example.shashankmohabia.clinmd.Data.DataModals.Doctor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.jetbrains.anko.toast


/**
 * Created by Shashank Mohabia on 7/19/2018.
 */

class LoadDoctorData {

    fun loadDoctorList(context: Context, id: String) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Patients").child(id).child("Doctors")
        if (FirebaseAuth.getInstance().currentUser != null) {
            dbRef.addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                    val doctor_id = dataSnapshot.key.toString()
                    loadDoctorDetails(context, doctor_id)
                    LoadDoctorPages().loadDoctorPages(context, id, doctor_id)
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        } else {
            context.toast("Problem Loading Data")
        }
    }

    private fun loadDoctorDetails(context: Context, doctor_id: String) {
        val doctorDb = FirebaseDatabase.getInstance().reference.child("Doctors").child("Doctor Id").child(doctor_id)
        doctorDb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val map = dataSnapshot.value as Map<*, *>?
                Doctor.doctorList.add(
                        Doctor(
                                map!!["registrationID"].toString(),
                                map["firstName"].toString(),
                                map["lastName"].toString(),
                                map["speciality"].toString(),
                                map["clinicAddress"].toString(),
                                map["phone"].toString(),
                                map["email"].toString(),
                                null
                        )
                )
            }
        })

    }
}


