package com.example.shashankmohabia.clinmd.Data.LocalDb

import android.app.Activity
import android.content.Context
import android.database.DatabaseUtils
import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor
import com.example.shashankmohabia.clinmd.Utils.App
import com.example.shashankmohabia.clinmd.Utils.Extensions.clear
import com.example.shashankmohabia.clinmd.Utils.Extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */

class DbFunctions(val ctx: Context = App.instance,
                  private val dbHelper: DbHelper = DbHelper.instance,
                  private val dbDataMapper: DbDataMapper = DbDataMapper()) {

    fun save(doctorList: MutableList<Doctor>) = dbHelper.use {

        /*clear(DoctorTable.NAME)*/
        for (doctor in doctorList) {
            with(dbDataMapper.convertFromDomain(doctor)) {
                insert(DoctorTable.NAME, *map.toVarargArray())
            }
        }

        ctx.toast("shashank")
        ctx.toast(DatabaseUtils.queryNumEntries(this, "Doctor").toString())

    }
}