package com.example.shashankmohabia.clinmd.Utils.Extensions

import android.database.sqlite.SQLiteDatabase

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}