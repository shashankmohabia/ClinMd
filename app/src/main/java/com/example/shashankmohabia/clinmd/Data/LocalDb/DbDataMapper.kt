package com.example.shashankmohabia.clinmd.Data.LocalDb

import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class DbDataMapper {
    fun convertFromDomain(doctor: Doctor) = with(doctor) {
        DoctorModel(id, first_name, last_name,specialization, clinic_address, phone, email, pageList)
    }

}