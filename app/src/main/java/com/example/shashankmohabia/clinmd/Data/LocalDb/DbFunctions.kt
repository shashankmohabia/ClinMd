package com.example.shashankmohabia.clinmd.Data.LocalDb
import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor
import com.example.shashankmohabia.clinmd.Utils.Extensions.clear
import com.example.shashankmohabia.clinmd.Utils.Extensions.toVarargArray
import org.jetbrains.anko.db.insert

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */

class DbFunctions(private val dbHelper: DbHelper = DbHelper.instance,
                  private val dbDataMapper:DbDataMapper = DbDataMapper()) {

    fun save(doctorList:MutableList<Doctor>) = dbHelper.use {

        clear(DoctorTable.NAME)
        for(doctor in doctorList){
            with(dbDataMapper.convertFromDomain(doctor)){
                insert(DoctorTable.NAME, *map.toVarargArray())
            }
        }

    }
}