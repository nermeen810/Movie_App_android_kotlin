package com.nermeen.movie_app.utils

class Constants {

    companion object{
        val base_url = "https://api.themoviedb.org"
        val api_key  = "c50f5aa4e7c95a2a553d29b81aad6dd0"

        fun createImageUrl(path: String)=  "https://image.tmdb.org/t/p/w200$path"
    }

}