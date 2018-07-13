package com.example.shashankmohabia.clinmd.Data.ServerClasses

import com.example.shashankmohabia.clinmd.Authentication.RegisterationDetailsActivity
import com.google.firebase.database.*

/**
 * Created by Shashank Mohabia on 7/10/2018.
 */
object ServerUtils {
    var childCount = 0
    fun countChild(dbref: DatabaseReference) {
        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                childCount = dataSnapshot.childrenCount.toInt()
            }
        })
    }
}