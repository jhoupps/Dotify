package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_songlist.*



class SongListActivity : AppCompatActivity() {
    lateinit var globalMenu: Menu
    var listOfSongs = SongDataProvider.getAllSongs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songlist)

     //   val listOfSongs = SongDataProvider.getAllSongs()

        val thesongAdapter = SongListAdapter(listOfSongs)

        rvSong.adapter = thesongAdapter

        setSupportActionBar(findViewById(R.id.bar))


        //first song
        // globalChosenSong = listOfSongs[0]
        

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)

        globalMenu = menu
        updateMenu(listOfSongs[0])
        return true
    }

    private fun updateMenu(chosenSong: Song): Boolean {
        var songTitle = chosenSong.title
        var songArtist = chosenSong.artist
        globalMenu.findItem(R.id.tvNowPlaying).setTitle("$songTitle - $songArtist")

        return true

    }

}
