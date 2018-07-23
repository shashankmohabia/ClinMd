package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineDetailView

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.clinmd.R

import kotlinx.android.synthetic.main.patient_history_detail_activity.*

class PatientHistoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_history_detail_activity)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
