package com.nermeen.movie_app.data.model

data class CategoryResponse(
    val page: Int,
    var results: List<Movies>,
    val total_pages: Int,
    val total_results: Int,
    var categoryName:String
)

data class Movies(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
