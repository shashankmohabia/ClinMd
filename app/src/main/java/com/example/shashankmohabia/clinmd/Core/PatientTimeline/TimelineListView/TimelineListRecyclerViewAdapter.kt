package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.dummy.DummyContent
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListFragment.TimelineListFragmentInteractionListener
import com.example.shashankmohabia.clinmd.R
import java.util.HashSet

class TimelineListRecyclerViewAdapter(private val mValues: MutableList<DummyContent.DummyItem>, private val mListener: TimelineListFragmentInteractionListener?) :
        RecyclerView.Adapter<TimelineListRecyclerViewAdapter.ViewHolder>() {

    private val unfoldedIndexes = HashSet<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.timeline_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*
        holder.mItem = mValues.get(position)
        holder.mTitleView.setText(mValues.get(position).title)
        holder.mContentView.setText(mValues.get(position).details)*/

        holder.mView.setOnClickListener {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener!!.onTimelineListFragmentInteraction(holder.mView)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun registerToggle(position: Int) {
        if (unfoldedIndexes.contains(position))
            registerFold(position)
        else
            registerUnfold(position)
    }

    fun registerFold(position: Int) {
        unfoldedIndexes.remove(position)
    }

    fun registerUnfold(position: Int) {
        unfoldedIndexes.add(position)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        /*val mTitleView: TextView
        val mContentView: TextView
        var mItem: DummyItem? = null*/

        init {
            /*mTitleView = mView.findViewById(R.id.title) as TextView
            mContentView = mView.findViewById(R.id.cell) as TextView*/
        }

        /* public override fun toString(): String {
             return super.toString() + " '" + mContentView.getText() + "'"
         }*/
    }
}
