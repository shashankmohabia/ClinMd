package com.example.shashankmohabia.clinmd.Data.DataModals

import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.dummy.DummyContent
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by Shashank Mohabia on 7/10/2018.
 */
class Doctor(
        val id: String,
        val first_name: String,
        val last_name: String,
        val specialization: String,
        val clinic_address: String,
        val phone: String,
        val email: String
) {
    companion object {
        val doctorList: MutableList<Doctor> = ArrayList()
        //val ITEM_MAP: MutableMap<String, DummyContent.DummyItem> = HashMap<String, DummyContent.DummyItem>()
    }

    private fun addItem(item: Doctor) {
        Doctor.doctorList.add(item)
        //DummyContent.ITEM_MAP.put(item.id, item)
    }

}