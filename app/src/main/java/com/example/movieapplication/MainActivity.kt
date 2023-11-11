package com.example.movieapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var page =1
    lateinit var binding: ActivityMainBinding
    var adapter= MovieAdapter()
    var list = ArrayList<ResultsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page++
//                loadingPB.setVisibility(View.VISIBLE)
                MovieApi(page)
            }
        })

        MovieApi(page)
    }

    private fun MovieApi(page: Int) {
        var api = MovieClient.getMovieCLient().create(MovieInterface::class.java)
        api.getMovieInterface(page).enqueue(object : Callback<MovieModel>{
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful){
                    var UpcomingList = response.body()?.results

                    list.addAll(UpcomingList as ArrayList<ResultsItem>)

                    adapter.setList(list)
                    binding.rcvMovie.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rcvMovie.adapter = adapter


                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {

            }

        })
    }

}