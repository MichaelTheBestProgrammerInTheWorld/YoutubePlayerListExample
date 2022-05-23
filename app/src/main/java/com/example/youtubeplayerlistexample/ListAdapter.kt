package com.example.youtubeplayerlistexample

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeplayerlistexample.databinding.ListItemBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX

class ListAdapter(fragmentT: Fragment)   : RecyclerView.Adapter<ListAdapter.ListHolder>() {


    //youtube player fragment
    var youtubeVideoArrayList = arrayListOf("CqhpNxI8qYw", "sasCrpgHDy8",
    "iJeh3x09Ya8", "B5zRaD7B504", "9qWHzj8k-Uo")


    val listData = arrayListOf<String>("Egypt", "Sudan", "Tunisia",
    "Algeria", "Morocco", "Mauritania", "Somalia", "Palestine", "Jordan",
    "Iraq", "Syria", "Lebanon", "Saudi Arabia", "Yemen", "Oman", "Arab Emirates",
    "Qatar", "Bahrain", "Kuwait")

    lateinit var context: Context
    val fragment: Fragment = fragmentT



    class ListHolder(itemView: ListItemBinding, fragment: Fragment) : RecyclerView.ViewHolder(itemView.root){

        private val binding = itemView
        private var youTubePlayer: YouTubePlayer? = null

        //private lateinit var youTubePlayerFragment: YouTubePlayerSupportFragmentX

        fun setData(data: String){

            binding.textList.text = data
        }

        val fragment: Fragment = fragment

        fun initializeYoutubePlayer(videoId: String) {

            //create a unique container id for each item
            val containerId = binding.youtubePlayerFragment.id
            val oldFragment = fragment.childFragmentManager.findFragmentById(containerId)
            if(oldFragment != null) {
                fragment.childFragmentManager.beginTransaction().remove(oldFragment).commit();
            }
            val newContainerId = View.generateViewId()
            binding.youtubePlayerFragment.id = newContainerId

            val youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance()
            val transaction = fragment.childFragmentManager.beginTransaction()
            transaction.replace(newContainerId, youTubePlayerFragment).commit()
            if (youTubePlayerFragment == null) return
            youTubePlayerFragment?.initialize(
                "AIzaSyAISkugfeK9PYfn-RSraAp3lBI0y3V_OOY",
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(
                        provider: YouTubePlayer.Provider, player: YouTubePlayer,
                        wasRestored: Boolean
                    ) {
                        if (!wasRestored) {
                            youTubePlayer = player


                            //set the player style default
                            youTubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

                            //cue the 1st video by default
                            youTubePlayer?.cueVideo(videoId)
                        }
                    }

                    override fun onInitializationFailure(
                        arg0: YouTubePlayer.Provider,
                        arg1: YouTubeInitializationResult
                    ) {

                        //print or show error if initialization failed
                        Log.e("YouTube_Err", "Youtube Player View initialization failed")
                    }
                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        //generateDummyVideoList()
        return ListHolder(view, fragment)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListHolder, position: Int) {

        //(holder as ListHolder).setData(listData.get(position))
        holder.initializeYoutubePlayer(youtubeVideoArrayList!![position])
    }

    override fun getItemCount(): Int {
        return youtubeVideoArrayList.size
    }


    private fun generateDummyVideoList() {
        youtubeVideoArrayList = ArrayList()

        //get the video id array from strings.xml
        val videoIDArray = context.resources.getStringArray(R.array.video_id_array)

        //add all videos to array list
        for (vidId in videoIDArray){
            youtubeVideoArrayList?.add(vidId)
        }
    }


}