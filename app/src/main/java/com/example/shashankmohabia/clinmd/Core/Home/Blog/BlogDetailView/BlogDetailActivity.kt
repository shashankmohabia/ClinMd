package com.example.shashankmohabia.clinmd.Core.Home.Blog.BlogDetailView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.clinmd.R
import kotlinx.android.synthetic.main.activity_blog_detail.*
import org.jetbrains.anko.toast

class BlogDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_detail)
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

    private fun getShareIntent() {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
        startActivity(Intent.createChooser(intent, "Share via"))
    }
}
