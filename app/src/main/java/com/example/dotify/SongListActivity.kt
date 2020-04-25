package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_songlist.*



class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songlist)

        val listOfSongs = SongDataProvider.getAllSongs()

        val thesongAdapter = SongListAdapter(listOfSongs)

        rvSong.adapter = thesongAdapter
    }
}
