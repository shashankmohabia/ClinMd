package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

import com.example.shashankmohabia.clinmd.R
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.dummy.DummyContent
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.dummy.DummyContent.DummyItem
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.timeline_fragment_item_list.view.*

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class TimelineListFragment : Fragment() {
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: TimelineListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.timeline_fragment_item_list, container, false)

        // Set the adapter
        if (view is ListView) {
            val context = view.getContext()
            val adapter = FoldingCellListAdapter(context, DummyContent.ITEMS)

            // set elements to adapter
            view.mainListView.setAdapter(adapter)

            // set on click event listener to list view
            view.mainListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, pos, l ->
                // toggle clicked cell state
                (view as FoldingCell).toggle(false)
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos)
            }
        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is TimelineListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement TimelineListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface TimelineListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onTimelineListFragmentInteraction(item: DummyItem)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): TimelineListFragment {
            val fragment = TimelineListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
