package com.example.movieapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movieapplication.databinding.MovieItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    lateinit var context: Context
    lateinit var upcomingList: List<ResultsItem?>
    class MovieHolder(itemView: MovieItemBinding) : ViewHolder(itemView.root){
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        context = parent.context
        var binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.apply {
            upcomingList.get(position)?.apply {
                Glide.with(context).load(MovieClient.IMAGE_BASE_URL+posterPath).into(imgPoster)
                txtTitle.text = originalTitle
                txtDes.text = overview
            }
        }
    }

    fun setList(upcomingList: List<ResultsItem?>?) {
        this.upcomingList = upcomingList as List<ResultsItem>
    }
}