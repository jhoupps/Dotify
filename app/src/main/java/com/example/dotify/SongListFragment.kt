package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.songdataprovider.Song
import android.content.Context
import kotlinx.android.synthetic.main.fragment_song_list.*

//The fragment displaying a list of songs
class SongListFragment : Fragment() {
    private lateinit var songListAdapter: SongListAdapter
    private var onSongClickListener: OnSongClickListener? = null
    private var listOfSongs: List<Song> = listOf()

    companion object {
        val TAG = SongListFragment::class.java.simpleName


          fun getInstance(): SongListFragment {
            return SongListFragment()
        }
    }

    //On attach, parse the arguments
    override fun onAttach(context: Context) {
        super.onAttach(context)

        arguments?.let { args ->
            val theSongs = args.getParcelableArrayList<Song>("theList")
            if (theSongs != null) {
                this.listOfSongs = theSongs.toList()
            }
        }

        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    //This code ended up being broken and I wasn't able to fix it
    //Currently, upon clicking the shuffle button, it crashes the app
  /*  fun shuffleList(){
        listOfSongs = listOfSongs.toMutableList().apply {
            shuffle()
        }.toList()

       // val updatedSongAdapter = SongListAdapter(listOfSongs)
     //  rvSongFrag.adapter = updatedSongAdapter

    }*/

    //When the view is created, inflate the view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    //The code for attaching the adapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songListAdapter = SongListAdapter(listOfSongs)
        rvSongFrag.adapter = songListAdapter

        songListAdapter.onSongClicked = { song:Song ->
            onSongClickListener?.onSongClicked(song)
        }
    }
}

//The listener for onSongClick
interface OnSongClickListener {
    fun onSongClicked(song: Song)
}
