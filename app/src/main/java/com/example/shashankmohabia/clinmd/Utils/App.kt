package com.example.shashankmohabia.clinmd.Utils

import android.app.Application
import com.example.shashankmohabia.clinmd.Utils.Extensions.DelegatesExt

/**
 * Created by Shashank Mohabia on 7/19/2018.
 */
open class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}