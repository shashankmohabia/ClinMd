package com.example.shashankmohabia.clinmd.Data.ServerClasses

import android.content.Context
import com.example.shashankmohabia.clinmd.Utils.App
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.toast

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class LoadDoctorPages(private val ctx: Context = App.instance) {

    fun loadDoctorPages(id: String, doctor_id: String){
        val dbRef = FirebaseDatabase.getInstance().reference.child("Patients").child(id).child("Doctors").child(doctor_id).child("Pages")
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
                    ctx.toast(page_id)
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        } else {
            ctx.toast("Problem Loading Data")
        }
    }
}