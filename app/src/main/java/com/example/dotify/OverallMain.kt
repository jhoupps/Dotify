package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.SongDataProvider

class OverallMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overall_main)

        var listOfSongs = SongDataProvider.getAllSongs()
        var mutableListOfSongs = listOfSongs.toMutableList()

        val songListFragment = SongListFragment()


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment)
            .commit()
    }
}
