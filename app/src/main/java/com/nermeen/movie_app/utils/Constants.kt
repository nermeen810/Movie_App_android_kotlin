package com.nermeen.movie_app.utils

class Constants {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val API_KEY  = "ad43b8622b4af4e0b3894a1f57a1ed18"
        const val LANGUAGE  = "en"

        fun createImageUrl(path: String)=  "https://image.tmdb.org/t/p/w200$path"
    }

}