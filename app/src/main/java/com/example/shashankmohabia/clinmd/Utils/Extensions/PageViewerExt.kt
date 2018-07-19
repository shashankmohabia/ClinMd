package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.example.shashankmohabia.clinmd.Utils.Adapters.PageViewerAdapter

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */

fun Fragment.slidingtabs(view_pager: ViewPager?, tabs: TabLayout?) {
    tabs?.setupWithViewPager(view_pager)
}

fun Fragment.setupViewPager(tabs: TabLayout?, tabs_title_list: List<String>, view_pager: ViewPager?, fragment1: Fragment, fragment2: Fragment) {
    val adapter = PageViewerAdapter(childFragmentManager)

    adapter.addFragment(fragment1, tabs_title_list[0])

    adapter.addFragment(fragment2, tabs_title_list[1])

    view_pager?.adapter = adapter
    slidingtabs(view_pager, tabs)
}

