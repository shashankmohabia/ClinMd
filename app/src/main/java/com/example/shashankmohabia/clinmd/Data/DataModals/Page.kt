package com.example.shashankmohabia.clinmd.Data.DataModals

import java.io.File
import java.util.*

/**
 * Created by Shashank Mohabia on 7/19/2018.
 */
data class Page(
        var id :String,
        var file :File,
        var date:Int,
        var doctor_id:String
){
    companion object {
        val pageList: MutableList<Page> = ArrayList()
        //val ITEM_MAP: MutableMap<String, DummyContent.DummyItem> = HashMap<String, DummyContent.DummyItem>()
    }

    /*fun addItem(item: Doctor) {
        Doctor.doctorList.add(item)
        //DummyContent.ITEM_MAP.put(item.id, item)
    }*/

}