package com.example.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_songlist.*



class SongListActivity : AppCompatActivity(){
    lateinit var globalMenu: Menu
    var listOfSongs = SongDataProvider.getAllSongs()
    var mutableListOfSongs = listOfSongs.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songlist)

        val thesongAdapter = SongListAdapter(mutableListOfSongs)

        /*

        // Create adapter (may want to save it as property)
        val personAdapter = PeopleAdapter(listOfPeople)

        // Set on item Click for the adapter
        personAdapter.onPersonClickListener = { somePerson: Person ->

            val intent = Intent(this, PersonActivity::class.java)
//            intent.putExtra(NAME_KEY, name)
////            intent.putExtra(POSITION_KEY, pos)
//
            intent.putExtra(PERSON_KEY, somePerson)

            startActivity(intent)


        }

         */

        thesongAdapter.onSongClickListener = {someSong: Song ->
            updateMenu(someSong)
        }

        rvSong.adapter = thesongAdapter

        setSupportActionBar(findViewById(R.id.bar))



    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)

        globalMenu = menu
        updateMenu(mutableListOfSongs[0])

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.tvNowPlaying) {
            //handle here
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            return true
        }
        else if (item.itemId == R.id.btnShuffle){
            updateMenu(mutableListOfSongs[2])// WORKs
            mutableListOfSongs.shuffle()

            val updatedSongAdapter = SongListAdapter(mutableListOfSongs)

            rvSong.adapter = updatedSongAdapter
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateMenu(chosenSong: Song): Boolean {
        var songTitle = chosenSong.title
        var songArtist = chosenSong.artist

        globalMenu.findItem(R.id.tvNowPlaying).title = "$songTitle - $songArtist"

        return true
    }

}
