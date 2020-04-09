package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton.setOnClickListener {
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }
        prevButton.setOnClickListener {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        var randomNumber = Random.nextInt(1000, 10000)

        tvRandomPlays.text="$randomNumber plays"

        playButton.setOnClickListener {
            randomNumber += 1
            tvRandomPlays.text="$randomNumber plays"
        }

        btn_ChangeUser.setOnClickListener{
            if(btn_ChangeUser.text == "Change User") {
                tv_User.visibility = View.GONE
                edit_User.visibility = View.VISIBLE
                btn_ChangeUser.text = "Apply"
            } else if (btn_ChangeUser.text == "Apply") {
                tv_User.text = edit_User.text

                tv_User.visibility = View.VISIBLE
                edit_User.visibility = View.GONE
                btn_ChangeUser.text = "Change User"
            }

        }

    }
}
