package com.example.dotify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NowPlayingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NowPlayingFragment : Fragment() {

    private var song: Song? = null

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName


        fun getInstance(song: Song) = NowPlayingFragment().apply {
            arguments = Bundle().apply {
                putParcelable("songKey", song)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val song = args.getParcelable<Song>("songKey")
            if (song != null) {
                this.song = song
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

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

        var randomNumber = Random.nextInt(1000, 10000)

        fragtvRandomPlays.text="$randomNumber plays"

        fragplayButton.setOnClickListener {
              randomNumber += 1
              fragtvRandomPlays.text="$randomNumber plays"
          }
    }

    fun updateSong(song: Song) {
        this.song = song
        updateSongViews()
    }

    private fun updateSongViews() {
        song?.let {
            song?.title?.let{it3 -> fragtvSongTitle.setText(it3)}
            song?.artist?.let{it2 -> fragtvArtist.setText(it2)}
            song?.largeImageID?.let { it1 -> fragimageView.setImageResource(it1) }
        }
    }

}