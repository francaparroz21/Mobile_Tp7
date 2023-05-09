package com.example.mobile_tp6.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mobile_tp6.R
import com.example.mobile_tp6.activity.MainActivity

class ErrorDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showsDialog = true
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.error_dialog)
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent: Intent = Intent(this.context, MainActivity::class.java)
                        startActivity(intent)
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id -> })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}