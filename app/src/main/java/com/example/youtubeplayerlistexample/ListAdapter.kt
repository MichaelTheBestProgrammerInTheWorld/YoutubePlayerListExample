package com.example.youtubeplayerlistexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeplayerlistexample.databinding.ListItemBinding

class ListAdapter   : RecyclerView.Adapter<ListAdapter.ListHolder>() {


    val listData = arrayListOf<String>("Egypt", "Sudan", "Tunisia",
    "Algeria", "Morocco", "Mauritania", "Somalia", "Palestine", "Jordan",
    "Iraq", "Syria", "Lebanon", "Saudi Arabia", "Yemen", "Oman", "Arab Emirates",
    "Qatar", "Bahrain", "Kuwait")

    class ListHolder(itemView: ListItemBinding) : RecyclerView.ViewHolder(itemView.root){

        private val binding = itemView

        fun setData(data: String){

            binding.textList.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListHolder, position: Int) {

        (holder as ListHolder).setData(listData.get(position))
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}