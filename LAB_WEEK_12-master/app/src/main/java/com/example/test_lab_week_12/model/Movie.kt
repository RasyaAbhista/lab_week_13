package com.example.test_lab_week_12.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
data class Movie(

    @PrimaryKey
    var id: Int = 0,

    var adult: Boolean = false,

    var backdrop_path: String? = null,

    @field:Json(name = "original_language")
    var originalLanguage: String? = null,

    @field:Json(name = "original_title")
    var originalTitle: String? = null,

    var overview: String? = null,

    var popularity: Float = 0f,

    @field:Json(name = "poster_path")
    var posterPath: String? = null,

    @field:Json(name = "release_date")
    var releaseDate: String? = null,

    var title: String? = null,
    var video: Boolean = false,

    @field:Json(name = "vote_average")
    var voteAverage: Float = 0f,

    @field:Json(name = "vote_count")
    var voteCount: Int = 0
) {

    // Constructor default untuk Moshi (tidak dipakai Room)
    @Ignore
    constructor() : this(
        id = 0,
        adult = false,
        backdrop_path = "",
        originalLanguage = "",
        originalTitle = "",
        overview = "",
        popularity = 0f,
        posterPath = "",
        releaseDate = "",
        title = "",
        video = false,
        voteAverage = 0f,
        voteCount = 0
    )
}
