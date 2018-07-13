package com.example.shashankmohabia.clinmd.Core.Reminder.Appointments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.shashankmohabia.clinmd.Core.Reminder.Appointments.AppointmentReminderListFragment.AppointmentReminderFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Core.Reminder.Appointments.dummy.DummyContent.DummyItem
import com.example.shashankmohabia.clinmd.R

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [AppointmentReminderFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class AppointmentReminderRecyclerViewAdapter(private val mValues: List<DummyItem>, private val mListener: AppointmentReminderFragmentInteractionListener?) : RecyclerView.Adapter<AppointmentReminderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.appointment_reminder_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].id
        holder.mContentView.text = mValues[position].content

        holder.mView.setOnClickListener {
            mListener?.onAppointmentReminderFragmentInteraction(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView
        var mItem: DummyItem? = null

        init {
            mIdView = mView.findViewById(R.id.id) as TextView
            mContentView = mView.findViewById(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
