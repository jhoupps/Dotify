package com.example.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
//import com.example.dotify.SongListActivity.*
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

//This one completely matches the example, with appropriate changes
//https://github.com/echeeUW/mailedItSpr20/blob/lecture8_MoreFragments/app/src/main/java/com/ericchee/mailedit/EmailAdapter.kt
class SongListAdapter(listOfSongs: List<Song>)
    : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClicked: ((song: Song) -> Unit)? = null
    private val listOfSongs = listOfSongs.toMutableList() //name differs from example conventions


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val theSong = listOfSongs[position] //what's this for again?
        holder.bind(theSong)
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)
        private val ivSongImage = itemView.findViewById<ImageView>(R.id.ivSongImage)


        fun bind(theSongBound: Song) {
            tvName.text = theSongBound.title
            tvArtist.text = theSongBound.artist
            ivSongImage.setImageResource((theSongBound.smallImageID))

            itemView.setOnClickListener {
                onSongClicked?.invoke(theSongBound)
            }

        }
    }

}