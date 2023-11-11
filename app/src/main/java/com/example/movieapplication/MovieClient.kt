package com.example.movieapplication

import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieClient {
    companion object{
        var BASE_URL = "https://api.themoviedb.org/3/movie/"
        var IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        lateinit var retrofit: Retrofit
        val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NzgyZmM5YTU3ZWQ4YmI5MWM4YjZjY2I1OWIxZTAxOCIsInN1YiI6IjY1MGMxZGM2ZjkyNTMyMDEwYmFmOTFjZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.x8XIzzKcJu9EwsolYA4DxWQ3mEU2vY1tAFKIIZ_cYFo"

        fun getMovieCLient(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor{chain->
                    val request = chain.request().newBuilder().addHeader("Authorization","Bearer ${TOKEN}").build()
                        chain.proceed(request)
                }.build())
                .build()
            return retrofit
        }
    }
}