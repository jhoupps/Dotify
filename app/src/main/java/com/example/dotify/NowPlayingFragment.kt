package com.example.dotify

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random

//The fragment which displays the now playing song
class NowPlayingFragment : Fragment() {
    private var song: Song? = null
    private var randomNumber: Int = 0

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName

        fun getInstance(song: Song) = NowPlayingFragment().apply {
            arguments = Bundle().apply {
                putParcelable("songKey", song)
            }
        }
    }

    //This function should save the Random Number of plays to the state in order to be restored
    //It does not work
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("randomNum", randomNumber)
        Toast.makeText(context, "saved!", Toast.LENGTH_SHORT).show()
    }

    //In theory, this function should create the fragment and should restore the random number
    //If the random number has been saved with the onSaveInstanceState function
    //It does not do that
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        randomNumber = if(savedInstanceState != null){
            savedInstanceState.getInt("randomNum")
        } else {
            Random.nextInt(1000, 10000)
        }

        //Parse the arguments
        arguments?.let { args ->
            val song = args.getParcelable<Song>("songKey")
            if (song != null) {
                this.song = song
            }
        }
    }

    //Create the view and inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    //Set various components according to the song data and implement button toasts
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        song.let {
            song?.title?.let{it3 -> fragtvSongTitle.setText(it3)}
            song?.artist?.let{it2 -> fragtvArtist.setText(it2)}
            song?.largeImageID?.let { it1 -> fragimageView.setImageResource(it1) }
        }

        //code for various buttons
        fragnextButton.setOnClickListener {
            Toast.makeText(context, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }
        fragprevButton.setOnClickListener {
            Toast.makeText(context, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        fragtvRandomPlays.text="$randomNumber plays"

        fragplayButton.setOnClickListener {
              randomNumber += 1
              fragtvRandomPlays.text="$randomNumber plays"
          }
    }

    //Update the song if the fragment is called twice
    fun updateSong(song: Song) {
        this.song = song
        updateSongViews()
    }

    //Update the song view if the fragment is called twice
    private fun updateSongViews() {
        song?.let {
            song?.title?.let{it3 -> fragtvSongTitle.setText(it3)}
            song?.artist?.let{it2 -> fragtvArtist.setText(it2)}
            song?.largeImageID?.let { it1 -> fragimageView.setImageResource(it1) }
        }
    }

}