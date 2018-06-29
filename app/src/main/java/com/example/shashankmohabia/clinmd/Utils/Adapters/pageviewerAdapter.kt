package com.example.shashankmohabia.clinmd.Utils.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Shashank Mohabia on 6/28/2018.
 */
class PageViewerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
}