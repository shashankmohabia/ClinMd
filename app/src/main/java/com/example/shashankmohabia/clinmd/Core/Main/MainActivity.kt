package com.example.shashankmohabia.clinmd.Core.Main

import android.app.Activity
import android.app.DatePickerDialog
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
import com.example.shashankmohabia.clinmd.Core.Reminder.ReminderFragment
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogDetailView.BlogDetailActivity
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogListView.BlogListFragment
import com.example.shashankmohabia.clinmd.Core.Home.HomeFragment
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedDetailView.NewsFeedDetailActivity
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.NewsListFragment
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListFragment
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListRecyclerViewAdapter
import com.example.shashankmohabia.clinmd.Core.Analytics.AnalyticsFragment
import com.example.shashankmohabia.clinmd.Core.Reminder.Appointments.AppointmentReminderListFragment
import com.example.shashankmohabia.clinmd.Core.Reminder.Piils.PillsReminderListFragment
import com.example.shashankmohabia.clinmd.Core.Reminder.Piils.dummy.DummyContent
import com.example.shashankmohabia.clinmd.UI.InformationActivity
import com.example.shashankmohabia.clinmd.Utils.FragmentListeners.FragmentListeners.setTimelineFragmentInteractions
import com.example.shashankmohabia.clinmd.Utils.Intents.Intents
import com.example.shashankmohabia.clinmd.Utils.UI.Dialogs.showProgressDialog
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_app_bar.*
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.add_document_fragment.*
import kotlinx.android.synthetic.main.add_family_member_fragment.*
import com.example.shashankmohabia.clinmd.Utils.Intents.Intents.getShareIntent
import kotlinx.android.synthetic.main.timeline_cell_content.view.*
import java.util.*
import android.app.TimePickerDialog
import com.example.shashankmohabia.clinmd.Data.ServerClasses.LoadPatientData
import com.example.shashankmohabia.clinmd.Utils.Extensions.makeInvisible
import com.example.shashankmohabia.clinmd.Utils.Formators.getDate
import com.example.shashankmohabia.clinmd.Utils.Formators.getTime
import com.example.shashankmohabia.clinmd.Utils.UI.Dialogs.showAppointmentRequestSentAlert
import com.ogaclejapan.arclayout.ArcLayout


class MainActivity:
        AppCompatActivity(),
        NewsListFragment.NewsFeedFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener,
        BlogListFragment.BlogFragmentInteractionListener,
        ReminderFragment.CalenderFragmentInteractionListener,
        HomeFragment.HomeFragmentInteractionListener,
        TimelineListFragment.TimelineListFragmentInteractionListener,
        AnalyticsFragment.SettingsFragmentInteractionListener,
        AddDocumentFragment.AddDocumentFragmentInteractionListener,
        AddFamilyMemberFragment.AddFamilyMemberFragmentInteractionListener,
        AppointmentReminderListFragment.AppointmentReminderFragmentInteractionListener,
        PillsReminderListFragment.PillsReminderListFragmentInteractionListener
{


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
        LoadPatientData().loadPatientDetails(this)
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

    override fun onPillsReminderFragmentInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAppointmentReminderFragmentInteraction(item: com.example.shashankmohabia.clinmd.Core.Reminder.Appointments.dummy.DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        item.mView.patient_appointment_button.setOnClickListener {
            getDatePickerIntent()
        }
    }

    fun getDatePickerIntent() {
        val calender = Calendar.getInstance()
        DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val date = getDate(year, monthOfYear, dayOfMonth)
                    getTimePickerIntent(date)
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setButton(DatePickerDialog.BUTTON_POSITIVE, "Continue", this)
            setTitle("Choose date")
            show()
        }
    }

    fun getTimePickerIntent(date: String) {
        val calender = Calendar.getInstance()
        val currentHour = calender.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calender.get(Calendar.MINUTE)
        TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    val time = getTime(selectedHour, selectedMinute)
                    showAppointmentRequestSentAlert(this, date, time)
                },
                currentHour,
                currentMinute,
                false
        ).apply {
            setButton(TimePickerDialog.BUTTON_POSITIVE, "request appointment", this)
            setButton(TimePickerDialog.BUTTON_NEGATIVE, "Change Date", this)
            setTitle("Choose Time")
            setOnCancelListener { getDatePickerIntent() }
            show()
        }
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
                        folding_menu.makeInvisible()
                    } else {
                        folding_menu.visibility = View.VISIBLE
                        makeBackgroundBlur()
                        add_family_member_button.setOnClickListener {
                            removeBackgroundBlur()
                            folding_menu.makeInvisible()
                            startFragmentTransaction(AddFamilyMemberFragment())
                        }
                        add_document_button.setOnClickListener {
                            removeBackgroundBlur()
                            folding_menu.makeInvisible()
                            startFragmentTransaction(AddDocumentFragment())
                        }
                    }
                }
                R.id.tab_home -> {
                    folding_menu.makeInvisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(HomeFragment())
                }
                R.id.tab_timeline -> {
                    folding_menu.makeInvisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(TimelineListFragment())
                }
                R.id.tab_calender -> {
                    folding_menu.makeInvisible()
                    removeBackgroundBlur()
                    startFragmentTransaction(ReminderFragment())
                }
                R.id.tab_analytics -> {
                    folding_menu.makeInvisible()
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

    private fun createSearchView(item: MenuItem?) {
        search_view.setMenuItem(item)
        search_view.setVoiceSearch(true)
        search_view.setSuggestions(resources.getStringArray(R.array.query_suggestions))
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        createSearchView(menu.findItem(R.id.action_search))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
        /* R.id.action_settings -> return true*/
            R.id.action_chat -> {
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

