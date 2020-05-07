package com.example.dotify

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_overall_main.*

//My main activity
class OverallMain : AppCompatActivity(), OnSongClickListener {
    var listOfSongs = SongDataProvider.getAllSongs()
    var chosenSong : Song? = null

    //The on create logic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overall_main)

        //Implement launching the song list fragment
        val songListFragment = SongListFragment.getInstance()

        val arrayListSongs = ArrayList(listOfSongs)
        val argumentBundle = Bundle().apply {
            putParcelableArrayList("theList", arrayListSongs)
        }
        songListFragment.arguments = argumentBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment, SongListFragment.TAG)
            .addToBackStack(SongListFragment.TAG)
            .commit()

        //Implement support for the backstack
        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0

            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        //Implement the on click listener for the bottom app bar
        //Which launches the Now Playing fragment
        btmAppBar.setOnClickListener {
            if (chosenSong != null) {
                btmAppBar.visibility = View.GONE

                var nowPlayingFragment = getNowPlayingFragment()
                if (nowPlayingFragment == null) {
                    nowPlayingFragment = NowPlayingFragment.getInstance(chosenSong!!)

                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                        .addToBackStack(NowPlayingFragment.TAG)
                        .commit()
                } else {
                    nowPlayingFragment.updateSong(chosenSong!!)
                }
            }
        }

        //This snippit of code was nearly the on click listener for the shuffle button
        //Currently, if uncommented, it crashes the app upon the shuffle button clicked
  /*    btnFragShuffle.setOnClickListener{
            var thesongListFragment = SongListFragment()
            thesongListFragment.shuffleList()
        }*/
    }

    //When a song is clicked, send the data thereof to the bottom app bar
    @SuppressLint("SetTextI18n")
    override fun onSongClicked(song: Song) {
        chosenSong = song
        val songTitle = song.title
        var songArtist = song.artist

        tvFragPlaying.text = "$songTitle - $songArtist"
    }
    //When back pressed, make the bottom app bar visible
    override fun onBackPressed() {
        super.onBackPressed()
        btmAppBar.visibility = View.VISIBLE
    }

    //A helper function for getting the now playing fragment
    private fun getNowPlayingFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    //A function for implementing backstack support
    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }
}
