package com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.NewsListFragment.NewsFeedFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.dummy.DummyContent.DummyItem
import com.example.shashankmohabia.clinmd.R
import java.util.HashSet

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [NewsFeedFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class NewsFeedRecyclerViewAdapter(private val mValues: MutableList<DummyItem>, private val mListener: NewsFeedFragmentInteractionListener?) :
        RecyclerView.Adapter<NewsFeedRecyclerViewAdapter.ViewHolder>() {


    private val unfoldedIndexes = HashSet<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {/*
        holder.mItem = mValues.get(position)
        holder.mIdView.setText(mValues.get(position).id)
        holder.mContentView.setText(mValues.get(position).content)*/

        holder.mView.setOnClickListener {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener!!.NewsFeedFragmentInteraction(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }
    // simple methods for register cell state changes
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


        init {

        }

        /*public override fun toString(): String {
            return super.toString() + " '" + mContentView.getText() + "'"
        }*/
    }
}
