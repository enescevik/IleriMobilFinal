package com.halic.ilerimobilfinal.components

import android.app.Activity
import android.app.Dialog
import android.widget.TextView
import com.halic.ilerimobilfinal.R

class CustomExitDialog {
    fun show(activity: Activity) {
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.custom_exit_dialog)

        val dialogButtonYes = dialog.findViewById<TextView>(R.id.textViewYes)
        val dialogButtonNo = dialog.findViewById<TextView>(R.id.textViewNo)

        dialogButtonNo.setOnClickListener {
            dialog.dismiss()
        }

        dialogButtonYes.setOnClickListener {
            dialog.dismiss()
            activity.finish()
        }

        dialog.setCancelable(false)
        dialog.show()
    }
}