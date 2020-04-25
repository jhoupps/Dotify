package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_songlist.*



class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songlist)

        val listOfSongs = listOf("Spongebob", "Squidward", "Eric Chee", "Clint", "Thanos was right", "Stark", "Chortle", "Patrick", "Dr. Oz", "Baby Yoda", "Dave", "null", "pointer", "This lecture sucks yoooo wtf is a list")

        val thesongAdapter = SongAdapter(listOfSongs)

        rvSong.adapter = thesongAdapter
    }
}
