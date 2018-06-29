package com.example.shashankmohabia.clinmd.Core.PatientFeed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.shashankmohabia.clinmd.Core.PatientFeed.FeedFragment.FeedFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Core.PatientFeed.dummy.DummyContent.DummyItem
import com.example.shashankmohabia.clinmd.R
import com.ramotion.foldingcell.FoldingCell

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [FeedFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class FeedRecyclerViewAdapter(private val mValues: List<DummyItem>, private val mListener: FeedFragmentInteractionListener?) : RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues.get(position)
        holder.mtitleView.setText(mValues.get(position).title)
        holder.mContentView.setText(mValues.get(position).content)

        holder.mView.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener!!.feedFragmentInteraction(holder)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mtitleView: TextView
        val mContentView: TextView
        var mItem: DummyItem? = null
        val cell: FoldingCell

        init {
            mtitleView = mView.findViewById(R.id.title) as TextView
            mContentView = mView.findViewById(R.id.content) as TextView
            cell = mView.findViewById(R.id.folding_cell) as FoldingCell
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.getText() + "'"
        }
    }
}
