package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineDetailView

import android.content.Context
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import com.example.shashankmohabia.clinmd.R
import com.leodroidcoder.genericadapter.BaseViewHolder
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.patient_history_item.view.*

/**
 * Created by Shashank Mohabia on 7/3/2018.
 */

class SimpleAdapter(context: Context, listener: OnRecyclerItemClickListener) : GenericRecyclerViewAdapter<String, OnRecyclerItemClickListener, DemoViewHolder>(context, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        return DemoViewHolder(inflate(R.layout.patient_history_item, parent), listener)
    }
}


class DemoViewHolder(itemView: View, listener: OnRecyclerItemClickListener?) : BaseViewHolder<String, OnRecyclerItemClickListener>(itemView, listener) {

    private val nameTv: TextView? = itemView.test

    init {
        listener?.let {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }

    override fun onBind(item: String) {
        nameTv?.text = item
    }
}