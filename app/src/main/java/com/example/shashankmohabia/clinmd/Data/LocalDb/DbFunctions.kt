package com.example.shashankmohabia.clinmd.Data.LocalDb
import com.example.shashankmohabia.clinmd.Data.DataModals.Doctor
import com.example.shashankmohabia.clinmd.Utils.Extensions.clear
import org.jetbrains.anko.db.insert

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class DbFunctions(private val dbHelper: DbHelper = DbHelper.instance) {

    fun save(doctorList:MutableList<Doctor>) = dbHelper.use {

        clear(DoctorTable.NAME)
        for(doctor in doctorList){
            with(doctor){
                insert(DoctorTable.NAME,)
            }
        }

    }
}