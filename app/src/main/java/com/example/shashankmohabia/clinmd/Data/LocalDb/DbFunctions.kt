package com.example.shashankmohabia.clinmd.Data.LocalDb

import android.content.Context
import android.util.Log
import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor
import com.example.shashankmohabia.clinmd.Data.DomainModals.Page
import com.example.shashankmohabia.clinmd.Utils.App
import com.example.shashankmohabia.clinmd.Utils.Extensions.clear
import com.example.shashankmohabia.clinmd.Utils.Extensions.parseList
import com.example.shashankmohabia.clinmd.Utils.Extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.net.InetAddress

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */

class DbFunctions(val ctx: Context = App.instance,
                  private val dbHelper: DbHelper = DbHelper.instance,
                  private val dbDataMapper: DbDataMapper = DbDataMapper()) {

    fun requestDoctorData(patientId: String) = dbHelper.use {
                select(DoctorTable.NAME)
                .whereSimple("${DoctorTable.PATIENT_ID} = ?", patientId)
                .parseList { DoctorModel(HashMap(it)) }

    }

    fun requestDoctorPages(doctorId: String, patientId: String) = dbHelper.use {
        val pageRequest = "${PageTable.DOCTOR_ID} = ? AND ${PageTable.PATIENT_ID} = ?"
        select(PageTable.NAME)
                .whereSimple(pageRequest, doctorId, patientId)
                .parseList { PageModal(HashMap(it)) }

    }


    fun save() = dbHelper.use {

        if (!InetAddress.getByName("google.com").equals("")) {
            clear(DoctorTable.NAME)
            clear(PageTable.NAME)


            for (doctor in Doctor.doctorList) {
                with(dbDataMapper.convertDoctorFromDomain(doctor)) {
                    insert(DoctorTable.NAME, *map.toVarargArray())
                    Log.d("heymama", "abcd")
                }
            }

            for (page in Page.pageList) {
                with(dbDataMapper.convertPageFromDomain(page)) {
                    Log.d("heymama", "efgh")
                    insert(PageTable.NAME, *map.toVarargArray())

                }
            }
        }


    }
}