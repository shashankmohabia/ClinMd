package com.example.shashankmohabia.clinmd.Core.Main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.clinmd.Data.PatientModal
import com.example.shashankmohabia.clinmd.R
import android.view.View
import com.example.shashankmohabia.clinmd.Core.Main.Adapters.Adapter
import com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogListView.BlogListFragment
import com.example.shashankmohabia.clinmd.Core.Home.Blog.dummy.DummyContent
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.NewsFeedListFragment
import com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedListView.NewsFeedListRecyclerViewAdapter
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
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.main_content.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BlogListFragment.BlogFragmentInteractionListener, NewsFeedListFragment.FeedFragmentInteractionListener {


    val dbRef = FirebaseDatabase.getInstance().reference.child("Patient").child("PatientID")
    lateinit var phone: String
    var familyList = ArrayList<PatientModal>()

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

        slidingtabs()

        folding_cards()

    }

    private fun slidingtabs() {
        setupViewPager(pager)
        //tabs.setupWithViewPager(pager)
    }

    private fun setupViewPager(pager: ViewPager?) {
        val adapter = Adapter(supportFragmentManager)

        val f1 = BlogListFragment()
        adapter.addFragment(f1, "Blog")

        val f2 = NewsFeedListFragment()
        adapter.addFragment(f2, "Feed")

        pager?.adapter = adapter
    }

    override fun blogFragmentInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun feedFragmentInteraction(item: NewsFeedListRecyclerViewAdapter.ViewHolder) {
        toast(item.mContentView.text)
    }


    private fun folding_cards() {

    }

    private fun bottomNavCustom() {


        bottomNavBar.setOnTabSelectListener { tabId ->
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
                            PatientModal(
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
                toast("Chat with your doctor")
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
