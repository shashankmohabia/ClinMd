package com.example.shashankmohabia.clinmd.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shashankmohabia.clinmd.R
import com.ramotion.paperonboarding.PaperOnboardingPage
import android.graphics.Color
import android.R.attr.key
import android.content.Intent
import android.support.design.R.id.add
import android.view.View
import com.example.shashankmohabia.clinmd.Authentication.LoginActivity
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener
import kotlinx.android.synthetic.main.activity_information.*


class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        createSlider()

        signInButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })

    }

    private fun createSlider() {
        val scr1 = PaperOnboardingPage("Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                Color.parseColor("#678FB4"), R.drawable.hotels, R.drawable.key)
        val scr2 = PaperOnboardingPage("Banks",
                "We carefully verify all banks before add them into the app",
                Color.parseColor("#65B0B4"), R.drawable.banks, R.drawable.wallet)
        val scr3 = PaperOnboardingPage("Stores",
                "All local stores are categorized for your convenience",
                Color.parseColor("#9B90BC"), R.drawable.stores, R.drawable.shopping_cart)

        val elements = ArrayList<PaperOnboardingPage>()
        elements.add(scr1)
        elements.add(scr2)
        elements.add(scr3)

        val onBoardingFragment = PaperOnboardingFragment.newInstance(elements)

        supportFragmentManager.beginTransaction()
                .replace(R.id.info_fragment, onBoardingFragment)
                .commit()

    }
}
