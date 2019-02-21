package com.teamwork.project.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.teamwork.project.ui.BaseFragment

fun Activity.showErrorDialog() = com.teamwork.project.utils.showErrorDialog(this)

fun BaseFragment<*>.showErrorDialog() = activity?.showErrorDialog()

fun showErrorDialog(context: Context) {
    AlertDialog.Builder(context)
        .setTitle("Error occurred!")
        .setMessage("Some network error has occurred. Please check your network connectivity.")
        .setNeutralButton("Ok", DialogInterface.OnClickListener { dialog, _ ->
            dialog.dismiss()
        })
        .create().show()
}