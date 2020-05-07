package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_overall_main.*

class OverallMain : AppCompatActivity(), OnSongClickListener {
    var listOfSongs = SongDataProvider.getAllSongs()
    var chosenSong : Song? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overall_main)


     //   var songListFragment = SongListFragment()

        val songListFragment = SongListFragment.getInstance()

        var arrayListSongs = ArrayList(listOfSongs)
        val argumentBundle = Bundle().apply {
            putParcelableArrayList("theList", arrayListSongs)
        }
        songListFragment.arguments = argumentBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment, SongListFragment.TAG)
            .addToBackStack(SongListFragment.TAG)
            .commit()

        //todo backstack
        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0

            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }



        //Now Playing Fragment Things


        btmAppBar.setOnClickListener {

            if (chosenSong != null) {
                btmAppBar.visibility = View.GONE

                // val nowPlayingFragment = NowPlayingFragment()
                var nowPlayingFragment = getNowPlayingFragment()

                /*  val argumentBundle = Bundle().apply {
                    putParcelable("songKey", chosenSong)
                }

                nowPlayingFragment.arguments = argumentBundle

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowPlayingFragment)
                    .commit()
            }*/


                if (nowPlayingFragment == null) {
                    nowPlayingFragment = NowPlayingFragment.getInstance(chosenSong!!)

                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                        .addToBackStack(NowPlayingFragment.TAG)
                        .commit()
                } else {
                    nowPlayingFragment.updateSong(chosenSong!!)
                    Toast.makeText(this, "update!", Toast.LENGTH_SHORT).show()

                }
            }
        }




  /*      btnFragShuffle.setOnClickListener{
            var thesongListFragment = SongListFragment()
            thesongListFragment.shuffleList()
        }*/
    }

    override fun onBackPressed() {
        super.onBackPressed()
        btmAppBar.visibility = View.VISIBLE

    }

    private fun getNowPlayingFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment



        //todo backstack
    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }


    override fun onSongClicked(song: Song) {
        //Toast.makeText(this, "click!",  Toast.LENGTH_SHORT ).show()

        chosenSong = song

        var songTitle = song.title
        var songArtist = song.artist

        //TODO MAKE IT PRETIER IF I WANT
        tvFragPlaying.text = "$songTitle - $songArtist"


       }

}
