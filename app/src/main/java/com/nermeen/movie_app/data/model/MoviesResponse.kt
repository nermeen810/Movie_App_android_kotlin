package com.nermeen.movie_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class MoviesResponse(
    val page: Int,
    var results: List<Movies>,
    val total_pages: Int,
    val total_results: Int,
)

data class CategoryResponse(
    val genres: List<Category>
)

@Entity
data class Category(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity
data class Movies(
    @PrimaryKey val id: Long,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
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
) : Serializable
