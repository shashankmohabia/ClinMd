package com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.NewsFeedDetailView

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.clinmd.R

import kotlinx.android.synthetic.main.news_feed_detail_activity.*

class NewsFeedDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_feed_detail_activity)

        fab.setOnClickListener { view ->
            getShareIntent()
        }
    }

    private fun getShareIntent() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Test")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Random extra text")
        startActivity(Intent.createChooser(intent, "Share via"))
    }
}
