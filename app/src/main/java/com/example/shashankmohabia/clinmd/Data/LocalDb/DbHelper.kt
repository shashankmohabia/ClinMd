package com.example.shashankmohabia.clinmd.Data.LocalDb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

import com.example.shashankmohabia.clinmd.Utils.App

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
class DbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        DbHelper.DB_NAME, null, DbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { DbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(DoctorTable.NAME, true,
                DoctorTable.ID to TEXT,
                DoctorTable.FIRST_NAME to TEXT,
                DoctorTable.LAST_NAME to TEXT,
                DoctorTable.SPEC to TEXT,
                DoctorTable.ADDRESS to TEXT,
                DoctorTable.PHONE to TEXT,
                DoctorTable.EMAIL to TEXT)

        /*db.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)*/
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(DoctorTable.NAME, true)
        onCreate(db)
    }
}

