package com.example.shashankmohabia.clinmd.Data.LocalDb

import java.sql.Blob
import java.util.HashMap

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class Doctor(map: MutableMap<String, Any?>, val PageList: List<Page>) {
    var _id: String by map
    var first_name: String by map
    var last_name: String by map
    var specialization: String by map
    var clinic_address: String by map
    var phone: String by map
    var email: String by map


    constructor(_id: String, first_name: String, last_name: String, specialization: String, clinic_address: String, phone: String, email: String, PageList: List<Page>)
            : this(HashMap(), PageList) {
        this._id = _id
        this.first_name = first_name
        this.last_name = last_name
        this.specialization = specialization
        this.clinic_address = clinic_address
        this.phone = phone
        this.email = email
    }
}

class Page(map: MutableMap<String, Any?>) {
    var _id: String by map
    var doctor_id: String by map
    var date: String by map
    var file: Blob by map

    constructor(_id: String, doctor_id: String, date: String, file: Blob)
            : this(HashMap()) {
        this._id = _id
        this.doctor_id = doctor_id
        this.date = date
        this.file = file
    }
}

