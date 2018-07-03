package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineDetailView

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.clinmd.R
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_patient_history.*
import kotlinx.android.synthetic.main.patient_history_content.*

class PatientHistoryActivity :
        AppCompatActivity(),
        OnRecyclerItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_history)
        setSupportActionBar(toolbar)

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
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
