package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

//TODO PUT THE INTERFACE LISTENER HERE

class OverallMain : AppCompatActivity(), OnSongClickListener {
    var listOfSongs = SongDataProvider.getAllSongs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overall_main)

        /*val emailDetailFragment = EmailDetailFragment()
        val argumentBundle = Bundle().apply {
            val email =  Email("marky@aol.com", "yooooo homieeeee")

            putParcelable(EmailDetailFragment.ARG_EMAIL, email)
        }
        emailDetailFragment.arguments = argumentBundle*/

        var songListFragment = SongListFragment()

       // if (songListFragment == null) {
       //     songListFragment = SongListFragment()
            var arrayListSongs = ArrayList(listOfSongs)
            val argumentBundle = Bundle().apply {
                putParcelableArrayList("theList", arrayListSongs)
            }
            songListFragment.arguments = argumentBundle
       // }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment)
            .commit()

        /* supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0

            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }*/
    }


    override fun onSongClicked(song: Song) {
        Toast.makeText(this, "click!",  Toast.LENGTH_SHORT ).show()


        /*
        var emailDetailFragment = getEmailDetailFragment()

        if (emailDetailFragment == null) {
            emailDetailFragment = EmailDetailFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(EmailDetailFragment.ARG_EMAIL, email)
            }
            emailDetailFragment.arguments = argumentBundle

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, emailDetailFragment, EmailDetailFragment.TAG)
                .addToBackStack(EmailDetailFragment.TAG)
                .commit()
        } else {
            emailDetailFragment.updateEmail(email)
        }
        */

    }

}
