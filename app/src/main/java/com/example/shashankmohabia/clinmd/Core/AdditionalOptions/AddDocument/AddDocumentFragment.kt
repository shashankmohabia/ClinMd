package com.example.shashankmohabia.clinmd.Core.AdditionalOptions.AddDocument

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.shashankmohabia.clinmd.R
import kotlinx.android.synthetic.main.fragment_add_document.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddDocumentFragment.AddDocumentFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddDocumentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddDocumentFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: AddDocumentFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_document, container, false)
        view.upload_document.setOnClickListener {
            onButtonPressed(view.upload_document.id)
        }
        view.new_document.setOnClickListener {
            onButtonPressed(view.new_document.id)
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    private fun onButtonPressed(uri: Int) {
        if (mListener != null) {
            mListener!!.onAddDocumentFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AddDocumentFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement AddDocumentFragmentInteractionListener")
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
    interface AddDocumentFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onAddDocumentFragmentInteraction(uri: Int)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddDocumentFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): AddDocumentFragment {
            val fragment = AddDocumentFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
