package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineDetailView

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.clinmd.Data.LocalDb.DbFunctions
import com.example.shashankmohabia.clinmd.Data.LocalDb.PageModal
import com.example.shashankmohabia.clinmd.R
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.patient_history_activity.*
import kotlinx.android.synthetic.main.patient_history_content.*
import org.jetbrains.anko.toast

class PatientHistoryActivity :
        AppCompatActivity(),
        OnRecyclerItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_history_activity)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val list = ArrayList<String>()
        for (i in 0..25) {
            list.add("item $i")
        }
        val hadapter = SimpleAdapter(this, this)
        historyList.adapter = hadapter
        hadapter.items = list



        PageModal.page_list = DbFunctions().requestDoctorPages("1234", "ClinMd1") as MutableList<PageModal>
        toast(PageModal.page_list.size.toString())
    }

    override fun onItemClick(position: Int) {
        startActivity(Intent(this, PatientHistoryDetailActivity::class.java))
    }
}
