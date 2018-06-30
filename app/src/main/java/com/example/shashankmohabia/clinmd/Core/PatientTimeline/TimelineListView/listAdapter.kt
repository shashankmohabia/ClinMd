package com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.dummy.DummyContent
import com.example.shashankmohabia.clinmd.R
import com.ramotion.foldingcell.FoldingCell
import java.util.HashSet

/**
 * Created by Shashank Mohabia on 6/30/2018.
 */

class FoldingCellListAdapter(context: Context, objects: List<DummyContent.DummyItem>) : ArrayAdapter<DummyContent.DummyItem>(context, 0, objects) {

    private val unfoldedIndexes = HashSet<Int>()
    var defaultRequestBtnClickListener: View.OnClickListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // get item for selected view
        val item = getItem(position)
        // if cell is exists - reuse it, if not - create the new one from resource
        var cell = convertView as FoldingCell?
        val viewHolder: ViewHolder
        if (cell == null) {
            viewHolder = ViewHolder()
            val vi = LayoutInflater.from(context)
            cell = vi.inflate(R.layout.timeline_fragment_item, parent, false) as FoldingCell
            // binding view parts to view holder
            /*viewHolder.title = cell.findViewById(R.id.cell_title)
            viewHolder.content = cell.findViewById(R.id.cell_content)*/
            cell.tag = viewHolder
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true)
            } else {
                cell.fold(true)
            }
            viewHolder = cell.tag as ViewHolder
        }

        if (null == item)
            return cell

        // bind data from selected element to view through view holder
        /*viewHolder.title!!.text = item!!.title
        viewHolder.content!!.text = item!!.details*/

        /*// set custom btn handler for list item from that item
        if (item!!.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn!!.setOnClickListener(item!!.getRequestBtnClickListener())
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn!!.setOnClickListener(defaultRequestBtnClickListener)
        }*/

        return cell
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

    // View lookup cache
    private class ViewHolder {
        internal var title: TextView? = null
        internal var content: TextView? = null
    }
}
