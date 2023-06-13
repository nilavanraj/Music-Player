package com.example.locationalert.common

import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.locationalert.R
import com.example.locationalert.data.MusicListModel

class MusicCardAdapter(private var mList: List<MusicListModel>) : RecyclerView.Adapter<MusicCardAdapter.ViewHolder>() {
    private val data = mList.toMutableList()
    val mediaPlayer = MediaPlayer()

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_card, parent, false)
        Log.d("mList",data.toString())

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.title
        if(ItemsViewModel.isSelected){
            val colorInt = Color.parseColor("#000000")
            holder.cardView.setCardBackgroundColor(ColorStateList.valueOf(colorInt))
        } else {
            val colorInt = Color.parseColor("#FFFFFF")
            holder.cardView.setCardBackgroundColor(ColorStateList.valueOf(colorInt))
        }
        holder.cardView.setOnClickListener {
            println(data[position])
            data[position].isSelected = !data[position].isSelected
            playSong(data[position].filePath)
            this.mList = data
            notifyDataSetChanged()
        }
    }

    private fun playSong(path:String) {
        if (mediaPlayer.isPlaying) {

            mediaPlayer.stop()
        } else{
            mediaPlayer.setDataSource(path)

            mediaPlayer.prepare()
            mediaPlayer.start()

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.music_name)
        val cardView: CardView = itemView.findViewById(R.id.music_card)
    }
}