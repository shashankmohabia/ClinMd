package com.example.shashankmohabia.clinmd.Data.ServerClasses

import android.content.Context
import com.example.shashankmohabia.clinmd.Data.DomainModals.Page
import com.example.shashankmohabia.clinmd.Utils.App
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import org.jetbrains.anko.toast

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class LoadDoctorPages(private val ctx: Context = App.instance) {

    fun loadDoctorPages(patient_id: String, doctor_id: String) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Patients").child(patient_id).child("Doctors").child(doctor_id).child("Pages")
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
                    val page_id = dataSnapshot.key.toString()
                    val map = dataSnapshot.value as Map<*, *>?
                    Page.pageList.add(
                            Page(
                                    page_id,
                                    doctor_id,
                                    patient_id,
                                    map!!["timestamp"].toString(),
                                    getPageData(doctor_id, map["page"].toString())
                            )
                    )
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        } else {
            ctx.toast("Problem Loading Data")
        }
    }

    private fun getPageData(doctor_id: String, page_name: String): ByteArray? {
        val pageStorageRef = FirebaseStorage.getInstance().reference.child("Doctors/$doctor_id/Original Pages/$page_name.png")
        var data: ByteArray? = null
        pageStorageRef.getBytes(java.lang.Long.MAX_VALUE).addOnSuccessListener({
            data = it
        }).addOnFailureListener({
            // Handle any errors
        })
        return data
    }
}