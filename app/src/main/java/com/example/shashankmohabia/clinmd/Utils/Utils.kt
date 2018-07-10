package com.example.shashankmohabia.clinmd.Utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.example.shashankmohabia.clinmd.Core.Main.MainActivity
import com.example.shashankmohabia.clinmd.R
import com.stepstone.apprating.AppRatingDialog
import java.util.*


/**
 * Created by Shashank Mohabia on 7/10/2018.
 */
object Utils {

    fun showProgressDialog(context: Context, title: String, msg: String, duration: Int) {

        val progress = ProgressDialog(context)
        progress.setTitle(title)
        progress.setMessage(msg)
        progress.show()

        val progressRunnable = Runnable { progress.cancel() }

        val pdCanceller = Handler()
        pdCanceller.postDelayed(progressRunnable, duration.toLong())

    }

    fun showRatingDialog(activity:FragmentActivity) {
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
                .create(activity)
                .show()
    }
}