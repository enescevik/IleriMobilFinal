package com.halic.ilerimobilfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.halic.ilerimobilfinal.components.CustomExitDialog
import com.halic.ilerimobilfinal.extensions.hasInternet

class EmptyDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_data)
        onBackPressedDispatcher.addCallback { customExitDialog() }

        findViewById<Button>(R.id.btnRetry)
            .setOnClickListener {
                if (this.hasInternet()) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
    }

    private fun customExitDialog() = CustomExitDialog().show(this)
}