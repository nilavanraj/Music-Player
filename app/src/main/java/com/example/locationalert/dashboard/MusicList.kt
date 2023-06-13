package com.example.locationalert.dashboard

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locationalert.R
import com.example.locationalert.common.MusicCardAdapter
import com.example.locationalert.data.MusicListModel


class MusicList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.music_list, container, false)
        val recyclerview =rootView.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(context)

        val data = getAllAudioFiles()
        Log.d("dataLog",data.toString())
        val adapter = MusicCardAdapter(data)

        recyclerview.adapter = adapter

        return rootView
    }

    fun getAudioFileDetailsWithThumbnail(filePath: String):MusicListModel? {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(filePath)

            val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)

            val rawArt = retriever.embeddedPicture
            val thumbnail: Bitmap? = if (rawArt != null) {
                BitmapFactory.decodeByteArray(rawArt, 0, rawArt.size)
            } else {
                null
            }

            return MusicListModel(title, artist, album, duration, thumbnail, filePath)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
        return null
    }

    fun getAllAudioFiles(): ArrayList<MusicListModel> {
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(MediaStore.Audio.Media.DATA)
        val cursor = context?.contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        val songs = ArrayList<MusicListModel>()

        cursor?.let {
            while (it.moveToNext()) {
                Log.d("test",getAudioFileDetailsWithThumbnail(it.getString(0)).toString())
                getAudioFileDetailsWithThumbnail(it.getString(0))?.let { it1 -> songs.add(it1) }
            }
            it.close()
        }
        return songs
    }

}