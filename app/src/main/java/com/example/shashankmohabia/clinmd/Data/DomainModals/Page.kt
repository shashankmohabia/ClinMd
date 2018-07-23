package com.example.shashankmohabia.clinmd.Data.DomainModals

import java.io.File
import java.util.*

/**
 * Created by Shashank Mohabia on 7/19/2018.
 */
data class Page(
        var id: String,
        var doctor_id: String,
        var patient_id: String,
        var timestamp: String,
        var file: ByteArray?
) {
    companion object {
        val pageList: MutableList<Page> = ArrayList()
        //val ITEM_MAP: MutableMap<String, DummyContent.DummyItem> = HashMap<String, DummyContent.DummyItem>()
    }

    /*fun addItem(item: DoctorModel) {
        DoctorModel.doctorList.add(item)
        //DummyContent.ITEM_MAP.put(item.id, item)
    }*/

}