package com.example.shashankmohabia.clinmd.Data.LocalDb

import com.example.shashankmohabia.clinmd.Data.DomainModals.Page
import java.sql.Blob
import java.util.HashMap

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class DoctorModel(val map: MutableMap<String, Any?>) {
    var _id: String by map
    var patient_id: String by map
    var first_name: String by map
    var last_name: String by map
    var specialization: String by map
    var clinic_address: String by map
    var phone: String by map
    var email: String by map

    companion object {
        var doctor_list: MutableList<DoctorModel> = ArrayList()
    }

    constructor(_id: String, patient_id: String, first_name: String, last_name: String, specialization: String, clinic_address: String, phone: String, email: String)
            : this(HashMap()) {
        this._id = _id
        this.patient_id = patient_id
        this.first_name = first_name
        this.last_name = last_name
        this.specialization = specialization
        this.clinic_address = clinic_address
        this.phone = phone
        this.email = email
    }
}

class PageModal(val map: MutableMap<String, Any?>) {
    var _id: String by map
    var doctor_id: String by map
    var patient_id: String by map
    var timestamp: String by map
    /*var file: Blob by map*/

    companion object {
        var page_list: MutableList<PageModal> = ArrayList()
    }

    constructor(_id: String, doctor_id: String, patient_id: String, timestamp: String)
            : this(HashMap()) {
        this._id = _id
        this.doctor_id = doctor_id
        this.patient_id = patient_id
        this.timestamp = timestamp
        /*this.file = file*/
    }
}

