package com.example.shashankmohabia.clinmd.Utils.FragmentListeners

import android.app.Activity
import android.content.Context
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineDetailView.PatientHistoryActivity
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListRecyclerViewAdapter
import com.example.shashankmohabia.clinmd.R
import com.example.shashankmohabia.clinmd.Utils.Extensions.*
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.timeline_cell_content.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.startActivity

/**
 * Created by Shashank Mohabia on 7/12/2018.
 */

object FragmentListeners :
        Activity() {

    fun setTimelineFragmentInteractions(context: Context, item: TimelineListRecyclerViewAdapter.ViewHolder) {
        (item.mView as FoldingCell).toggle(false)

        item.mView.patient_read_more_button.setOnClickListener {
            val msg = context.getString(R.string.doctor_complete_summary)
            context.alert(msg) {
                title = "Complete Prescription Summary"
                positiveButton("Cool") { }
            }.show()
        }

        item.mView.patient_appointment_button.setOnClickListener {
            context.getDatePickerIntent()
        }

        item.mView.patient_location_button.setOnClickListener {
            val address = "MNIT, Jaipur, Rajasthan"
            context.getMapIntent(address)
        }

        item.mView.patient_history_button.setOnClickListener {
            context.startActivity<PatientHistoryActivity>()
        }

        item.mView.patient_call_button.setOnClickListener {
            val number = item.item!!.phone
            context.makeCall(number)
        }

        item.mView.patient_chat_button.setOnClickListener {
            //number must be on whatsapp
            val number = "+918504939946"                 //919461388766
            if (context.doesContactExists(number)) {           //919694169864
                context.getWhatsAppIntent(number.substringAfterLast('+'))
            } else {
                context.alert("You have to save this number to chat with your DoctorModel") {
                    title = "Number Not Found"
                    positiveButton("Save this number") {
                        val name = "DoctorModel"
                        val email = "doc@gmail.com"
                        context.getSaveContactIntent(name, number, email)
                    }
                    negativeButton("I'll do it later") {}
                }.show()
            }
        }

        item.mView.patient_all_share_button.setOnClickListener {
            context.getShareIntent()
        }
    }
}