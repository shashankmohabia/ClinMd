package com.example.shashankmohabia.clinmd.Core.Main

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
import com.example.shashankmohabia.clinmd.Data.DataModals.Patient
import com.example.shashankmohabia.clinmd.R
import android.view.View
import com.example.shashankmohabia.clinmd.Core.AdditionalOptions.BookAppointment.BookAppointmentActivity
import com.example.shashankmohabia.clinmd.Core.AdditionalOptions.Chat.ChatActivity
import com.example.shashankmohabia.clinmd.Core.Calender.CalenderFragment
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogDetailView.BlogDetailActivity
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogListView.BlogListFragment
import com.example.shashankmohabia.clinmd.Core.Home.HomeFragment
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedDetailView.NewsFeedDetailActivity
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.NewsListFragment
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineDetailView.PatientHistoryActivity
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListFragment
import com.example.shashankmohabia.clinmd.Core.PatientTimeline.TimelineListView.TimelineListRecyclerViewAdapter
import com.example.shashankmohabia.clinmd.Core.Settings.SettingsFragment
import com.example.shashankmohabia.clinmd.UI.InformationActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_app_bar.*
import org.jetbrains.anko.toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseError
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.timeline_cell_content.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.util.Arrays.asList
import com.stepstone.apprating.AppRatingDialog
import com.stepstone.apprating.listener.RatingDialogListener
import java.util.*


class MainActivity :
        AppCompatActivity(),
        NewsListFragment.NewsFeedFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener,
        BlogListFragment.BlogFragmentInteractionListener,
        CalenderFragment.CalenderFragmentInteractionListener,
        HomeFragment.HomeFragmentInteractionListener,
        TimelineListFragment.TimelineListFragmentInteractionListener,
        SettingsFragment.SettingsFragmentInteractionListener,
        RatingDialogListener {



    val dbRef = FirebaseDatabase.getInstance().reference.child("Patient").child("PatientID")
    lateinit var phone: String
    var familyList = ArrayList<Patient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)




        loadPatientDetails()
        displayPatientDetails()

        bottomNavCustom()


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
        getShareIntent()
    }

    private fun getShareIntent() {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
        startActivity(Intent.createChooser(intent, "Share via"))
    }

    override fun onTimelineListFragmentInteraction(item: TimelineListRecyclerViewAdapter.ViewHolder) {

        (item.mView as FoldingCell).toggle(false)

        item.mView.patient_read_more_button.setOnClickListener {
            val msg = getString(R.string.doctor_complete_summary)
            alert(msg) {
                title = "Complete Prescription Summary"
                positiveButton("Cool") { }
            }.show()
        }

        item.mView.patient_appointment_button.setOnClickListener {
            startActivity(Intent(this, BookAppointmentActivity::class.java))
        }

        item.mView.patient_location_button.setOnClickListener { }

        item.mView.patient_history_button.setOnClickListener {
            startActivity(Intent(this, PatientHistoryActivity::class.java))
        }

        item.mView.patient_call_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8504939946")
            startActivity(intent)
        }

        item.mView.patient_chat_button.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        item.mView.patient_rating_button.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                /*.setNegativeButtonText("Cancel")
               *//* .setNeutralButtonText("Later")*/
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("Rate Your Doctor")
                .setDescription("Please select some stars and give your feedback")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.starColor)
                .setTitleTextColor(R.color.titleTextColor)
                .setDescriptionTextColor(R.color.black_overlay)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.hintTextColor)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.commentBackground)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(this@MainActivity)
                .show()
    }

    //functions for rating dialouge
    override fun onNegativeButtonClicked() {

    }

    override fun onNeutralButtonClicked() {

    }

    override fun onPositiveButtonClicked(rate: Int, comment: String) {
        toast("Your Response Is Submitted")
    }

    private fun bottomNavCustom() {
        bottomNavBar.selectTabWithId(R.id.tab_home)
        startFragmentTransaction(HomeFragment())
        bottomNavBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_plus -> {
                    if (folding_menu.visibility == View.VISIBLE) {
                        folding_menu.visibility = View.INVISIBLE
                    } else {
                        folding_menu.visibility = View.VISIBLE
                    }
                }
                R.id.tab_home -> {
                    startFragmentTransaction(HomeFragment())
                }
                R.id.tab_timeline -> {
                    startFragmentTransaction(TimelineListFragment())
                }
                R.id.tab_calender -> {
                    startFragmentTransaction(CalenderFragment())
                }
                R.id.tab_settings -> {
                    startFragmentTransaction(SettingsFragment())
                }
            }

        }

        bottomNavBar.setOnTabReselectListener { tabId ->
            when (tabId) {
                R.id.tab_plus -> {
                    if (folding_menu.visibility == View.VISIBLE) {
                        folding_menu.visibility = View.INVISIBLE
                    } else {
                        folding_menu.visibility = View.VISIBLE
                    }
                }


            }
        }
    }

    private fun startFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit()
    }

    private fun displayPatientDetails() {

    }

    private fun loadPatientDetails() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            phone = FirebaseAuth.getInstance().currentUser?.phoneNumber.toString()
            dbRef.orderByChild("phone").equalTo(phone).addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                    val map = dataSnapshot.value as Map<*, *>?
                    familyList.add(
                            Patient(
                                    map!!["first_name"].toString(),
                                    map["last_name"].toString(),
                                    map["phone"].toString(),
                                    map["age"].toString()
                            )
                    )
                    toast("size = " + familyList.size)
                    toast(dataSnapshot.value.toString())
                }

            })
        } else {
            toast("Problem Loading Data")
        }
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
        search_view.setSuggestions(getResources().getStringArray(R.array.query_suggestions))
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
