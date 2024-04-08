package com.example.customnetworkapimovie.model.data


data class MovieDetailsData(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: CollectionData,
    val budget: Int,
    val genres: List<GenreData>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyData>,
    val productionCountries: List<ProductionCountryData>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageData>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class GenreData(
    val id: Int,
    val name: String
)

data class ProductionCompanyData(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

data class ProductionCountryData(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguageData(
    val englishName: String,
    val iso_639_1: String,
    val name: String
)

data class CollectionData(
    val id: String,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)