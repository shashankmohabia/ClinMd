package com.example.shashankmohabia.clinmd.Core.Reminder.Piils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.shashankmohabia.clinmd.Core.Reminder.Piils.PillsReminderListFragment.PillsReminderListFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Core.Reminder.Piils.dummy.DummyContent.DummyItem
import com.example.shashankmohabia.clinmd.R

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [PillsReminderListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PillsReminderRecyclerViewAdapter(private val mValues: List<DummyItem>, private val mListener: PillsReminderListFragmentInteractionListener?) : RecyclerView.Adapter<PillsReminderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pills_reminder_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].id
        holder.mContentView.text = mValues[position].content

        holder.mView.setOnClickListener {
            mListener?.onPillsReminderFragmentInteraction(holder.mItem!!)
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
