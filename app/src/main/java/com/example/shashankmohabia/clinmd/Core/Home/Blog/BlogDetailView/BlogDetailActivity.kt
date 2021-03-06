package com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogDetailView

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.clinmd.R
import com.example.shashankmohabia.clinmd.Utils.Extensions.getShareIntent
import kotlinx.android.synthetic.main.blog_detail_activity.*

class BlogDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blog_detail_activity)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Bookmark this blog", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_blog_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.share -> {
                getShareIntent()
            }
        }
        return true
    }

}
