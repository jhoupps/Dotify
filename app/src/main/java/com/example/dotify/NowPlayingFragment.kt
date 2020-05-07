package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.fragment_now_playing.*

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

   /* companion object {
        const val ARG_EMAIL = "arg_email"
    } */

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
    }



}