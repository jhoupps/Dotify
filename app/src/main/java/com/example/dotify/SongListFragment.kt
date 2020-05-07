package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.songdataprovider.Song
import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_song_list.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [SongListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

  /*  fun shuffleList(){
        listOfSongs = listOfSongs.toMutableList().apply {
            shuffle()
        }.toList()


       // val updatedSongAdapter = SongListAdapter(listOfSongs)
     //  rvSongFrag.adapter = updatedSongAdapter

    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songListAdapter = SongListAdapter(listOfSongs)
        rvSongFrag.adapter = songListAdapter

        songListAdapter.onSongClicked = { song:Song ->
            onSongClickListener?.onSongClicked(song)
        }

      /*  btnCompose.setOnClickListener {
            startActivityForResult(Intent(context, ComposeActivity::class.java),
                ListEmailsActivity.COMPOSE_REQUEST_CODE
            )
        }*/
    }

}

interface OnSongClickListener {
    fun onSongClicked(song: Song)
}
