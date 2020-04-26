package com.example.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongListAdapter(private val listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val theSong = listOfSongs[position]
        holder.bind(theSong)
    }

    class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)
        private val ivSongImage = itemView.findViewById<ImageView>(R.id.ivSongImage)


        fun bind(theSongBound: Song) {
            tvName.text = theSongBound.title
            tvArtist.text = theSongBound.artist
            ivSongImage.setImageResource((theSongBound.smallImageID))

        }

    }

}