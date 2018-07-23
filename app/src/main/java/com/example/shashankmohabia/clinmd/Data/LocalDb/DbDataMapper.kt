package com.example.shashankmohabia.clinmd.Data.LocalDb

import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor
import com.example.shashankmohabia.clinmd.Data.DomainModals.Page

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class DbDataMapper {
    fun convertDoctorFromDomain(doctor: Doctor) = with(doctor) {
        DoctorModel(id, patient_id, first_name, last_name, specialization, clinic_address, phone, email)
    }

    fun convertPageFromDomain(page: Page) = with(page) {
        PageModal(id, doctor_id, patient_id, timestamp, file)
    }

}