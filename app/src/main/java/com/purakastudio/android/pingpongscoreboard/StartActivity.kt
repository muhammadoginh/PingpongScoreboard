package com.purakastudio.android.pingpongscoreboard

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        done.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("player1", inputPlayer1.text.toString())
            intent.putExtra("player2", inputPlayer2.text.toString())
            intent.putExtra("service", serviceSpinner.selectedItem.toString())
            startActivity(intent)
        }

        // untuk service
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.service_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            serviceSpinner.adapter = adapter
        }
    }
}