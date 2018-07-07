package com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogListView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogListView.BlogListFragment.BlogFragmentInteractionListener
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.dummy.DummyContent
import com.example.shashankmohabia.clinmd.R
import kotlinx.android.synthetic.main.blog_item.view.*



class BlogListRecyclerViewAdapter(
        private val mValues: MutableList<DummyContent.DummyItem>,
        private val mListener: BlogFragmentInteractionListener?)
    : RecyclerView.Adapter<BlogListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.blog_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       /* holder.mItem = mValues[position]
        holder.mTitleView.text = mValues[position].id
        holder.mContentView.text = mValues[position].title
*/
        holder.mView.read_more.setOnClickListener {
            mListener?.blogReadMoreInteraction(position)
        }

        holder.mView.blog_share_button.setOnClickListener {
            mListener?.blogShareButtonInteraction(position)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        /*val mTitleView : TextView
        val mContentView: TextView
        var mItem: DummyItem? = null

        init {
            mTitleView = mView.findViewById(R.id.id) as TextView
            mContentView = mView.findViewById(R.id.title) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }*/
    }
}
