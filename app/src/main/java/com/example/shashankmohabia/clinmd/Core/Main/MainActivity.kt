package com.example.shashankmohabia.clinmd.Core.Main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.clinmd.R
import android.view.View
import com.example.shashankmohabia.clinmd.Core.AdditionalOptions.AddDocument.AddDocumentFragment
import com.example.shashankmohabia.clinmd.Core.AdditionalOptions.AddFamilyMember.AddFamilyMemberFragment
import com.example.shashankmohabia.clinmd.Core.AdditionalOptions.Chat.ChatActivity
import com.example.shashankmohabia.clinmd.Core.Calender.CalenderFragment
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogDetailView.BlogDetailActivity
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogListView.BlogListFragment
import com.example.shashankmohabia.clinmd.Core.Home.HomeFragment
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedDetailView.NewsFeedDetailActivity
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.NewsListFragment
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListFragment
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListRecyclerViewAdapter
import com.example.shashankmohabia.clinmd.Core.Analytics.AnalyticsFragment
import com.example.shashankmohabia.clinmd.UI.InformationActivity
import com.example.shashankmohabia.clinmd.Utils.FragmentListeners.FragmentListeners.setTimelineFragmentInteractions
import com.example.shashankmohabia.clinmd.Utils.Intents.Intents
import com.example.shashankmohabia.clinmd.Utils.UI.Dialogs.showProgressDialog
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_app_bar.*
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.main_content.*
import com.stepstone.apprating.listener.RatingDialogListener
import kotlinx.android.synthetic.main.add_document_fragment.*
import kotlinx.android.synthetic.main.add_family_member_fragment.*
import com.example.shashankmohabia.clinmd.Utils.Intents.Intents.getShareIntent


class MainActivity :
        AppCompatActivity(),
        NewsListFragment.NewsFeedFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener,
        BlogListFragment.BlogFragmentInteractionListener,
        CalenderFragment.CalenderFragmentInteractionListener,
        HomeFragment.HomeFragmentInteractionListener,
        TimelineListFragment.TimelineListFragmentInteractionListener,
        AnalyticsFragment.SettingsFragmentInteractionListener,
        AddDocumentFragment.AddDocumentFragmentInteractionListener,
        AddFamilyMemberFragment.AddFamilyMemberFragmentInteractionListener,
        RatingDialogListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        removeBackgroundBlur()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        setBottomNavBar()


    }


    override fun onAddDocumentFragmentInteraction(uri: Int) {
        when (uri) {
            upload_document.id -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                Intents.startActivityForResult(intent, 1)
            }
            new_document.id -> {
                val intent = Intent("android.media.action.IMAGE_CAPTURE")
                startActivityForResult(intent, 0)
            }
        }
    }

    override fun onAddFamilyMemberFragmentInteraction(uri: Int) {
        when (uri) {
            add_family_member_submit.id -> {
                val title = "Please Wait"
                val msg = "Checking in our records"
                val duration = 3000
                showProgressDialog(this, title, msg, duration)
            }
        }
    }

    override fun NewsFeedFragmentInteraction(item: View) {
        startActivity(Intent(this, NewsFeedDetailActivity::class.java))
    }

    override fun onHomeFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCalenderFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSettingsFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun blogReadMoreInteraction(item: Int) {
        startActivity(Intent(this, BlogDetailActivity::class.java))
    }

    override fun blogShareButtonInteraction(position: Int) {
        getShareIntent(this)
    }

    override fun onTimelineListFragmentInteraction(item: TimelineListRecyclerViewAdapter.ViewHolder) {
        setTimelineFragmentInteractions(this, item)
    }


    //functions for rating dialouge
    override fun onNegativeButtonClicked() {

    }

    override fun onNeutralButtonClicked() {

    }

    override fun onPositiveButtonClicked(rate: Int, comment: String) {
        toast("Your Response Is Submitted")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                0 -> {
                    if (data != null) {
                        toast(data.toURI())
                    }
                }
                1 -> {
                    toast(data.toString())
                }
            }
        }
    }

    fun makeFoldingMenuInVisible() {
        folding_menu.visibility = View.INVISIBLE
    }

    fun makeBackgroundBlur() {
        mainFrame.foreground.alpha = 220
    }

    fun removeBackgroundBlur() {
        mainFrame.foreground.alpha = 0
    }

    fun setBottomNavBar() {
        bottomNavBar.selectTabWithId(R.id.tab_home)
        startFragmentTransaction(HomeFragment())
        bottomNavBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_plus -> {
                    if (folding_menu.visibility == View.VISIBLE) {
                        makeFoldingMenuInVisible()
                    } else {
                        folding_menu.visibility = View.VISIBLE
                        makeBackgroundBlur()
                        add_family_member_button.setOnClickListener {
                            removeBackgroundBlur()
                            makeFoldingMenuInVisible()
                            startFragmentTransaction(AddFamilyMemberFragment())
                        }
                        add_document_button.setOnClickListener {
                            removeBackgroundBlur()
                            makeFoldingMenuInVisible()
                            startFragmentTransaction(AddDocumentFragment())
                        }
                    }
                }
                R.id.tab_home -> {
                    makeFoldingMenuInVisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(HomeFragment())
                }
                R.id.tab_timeline -> {
                    makeFoldingMenuInVisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(TimelineListFragment())
                }
                R.id.tab_calender -> {
                    makeFoldingMenuInVisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(CalenderFragment())
                }
                R.id.tab_analytics -> {
                    makeFoldingMenuInVisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(AnalyticsFragment())
                }
            }

        }

        bottomNavBar.setOnTabReselectListener { tabId ->
            when (tabId) {
                R.id.tab_plus -> {
                    if (folding_menu.visibility == View.VISIBLE) {
                        removeBackgroundBlur()
                        folding_menu.visibility = View.INVISIBLE
                    } else {
                        makeBackgroundBlur()
                        folding_menu.visibility = View.VISIBLE
                    }
                }


            }
        }
    }

    fun startFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        createSearchView(menu.findItem(R.id.action_search))
        return true
    }

    private fun createSearchView(item: MenuItem?) {
        search_view.setMenuItem(item)
        search_view.setVoiceSearch(true)
        search_view.setSuggestions(resources.getStringArray(R.array.query_suggestions))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
        /* R.id.action_settings -> return true*/
            R.id.action_chat -> {
                startActivity(Intent(this, ChatActivity::class.java))
                return true
            }
            else -> return true

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
            R.id.nav_logout -> {
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener {
                            startActivity(Intent(this@MainActivity, InformationActivity::class.java))
                            finish()
                        }
            }
            R.id.nav_delete_account -> {
                AuthUI.getInstance()
                        .delete(this)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this@MainActivity, InformationActivity::class.java))
                                finish()

                            } else {
                                toast("Unable to delete account")
                            }
                        }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
