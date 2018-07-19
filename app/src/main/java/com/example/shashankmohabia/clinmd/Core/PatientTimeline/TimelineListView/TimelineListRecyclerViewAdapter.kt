package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListFragment.TimelineListFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Data.DataModals.Doctor
import com.example.shashankmohabia.clinmd.R
import kotlinx.android.synthetic.main.timeline_cell_content.view.*
import kotlinx.android.synthetic.main.timeline_cell_title.view.*
import java.util.HashSet

class TimelineListRecyclerViewAdapter(private val mValues: MutableList<Doctor>, private val mListener: TimelineListFragmentInteractionListener?) :
        RecyclerView.Adapter<TimelineListRecyclerViewAdapter.ViewHolder>() {

    private val unfoldedIndexes = HashSet<Int>()
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

        fun bindHolder(position: Int) {
            with(mValues[position]){
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

        /* public override fun toString(): String {
             return super.toString() + " '" + mContentView.getText() + "'"
         }*/
    }
}
