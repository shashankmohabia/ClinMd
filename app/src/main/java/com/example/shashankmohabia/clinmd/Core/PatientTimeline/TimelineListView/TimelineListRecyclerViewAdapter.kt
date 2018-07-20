package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListFragment.TimelineListFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Data.DomainModals.Doctor
import com.example.shashankmohabia.clinmd.Data.LocalDb.DoctorModel
import com.example.shashankmohabia.clinmd.R
import kotlinx.android.synthetic.main.timeline_cell_content.view.*
import kotlinx.android.synthetic.main.timeline_cell_title.view.*

class TimelineListRecyclerViewAdapter(private val mValues: MutableList<DoctorModel>, private val mListener: TimelineListFragmentInteractionListener?) :
        RecyclerView.Adapter<TimelineListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.timeline_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(position)

        holder.mView.setOnClickListener {
            mListener?.onTimelineListFragmentInteraction(holder)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var item: DoctorModel? = null
        fun bindHolder(position: Int) {
            item = mValues[position]
            with(mValues[position]) {
                itemView.apply {
                    title_doctor_name.text = "$first_name $last_name"
                    title_doctor_spec.text = specialization

                    content_doctor_name.text = "$first_name $last_name"
                    content_doctor_spec.text = specialization
                    content_doctor_address.text = clinic_address
                    content_doctor_contact.text = "$phone $email"
                }
            }
        }
    }
}
