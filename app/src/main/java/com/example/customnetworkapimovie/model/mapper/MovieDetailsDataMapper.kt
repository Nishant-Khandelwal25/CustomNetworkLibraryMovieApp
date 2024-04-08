package com.example.customnetworkapimovie.model.mapper

import com.example.customnetworkapimovie.model.data.CollectionData
import com.example.customnetworkapimovie.model.data.GenreData
import com.example.customnetworkapimovie.model.data.MovieDetailsData
import com.example.customnetworkapimovie.model.data.ProductionCompanyData
import com.example.customnetworkapimovie.model.data.ProductionCountryData
import com.example.customnetworkapimovie.model.data.SpokenLanguageData
import com.example.customnetworkapimovie.model.response.Collection
import com.example.customnetworkapimovie.model.response.Genre
import com.example.customnetworkapimovie.model.response.MovieDetailsResponse
import com.example.customnetworkapimovie.model.response.ProductionCompany
import com.example.customnetworkapimovie.model.response.ProductionCountry
import com.example.customnetworkapimovie.model.response.SpokenLanguage
import com.example.customnetworkapimovie.util.safeMap

fun MovieDetailsResponse.toMovieDetailsResponseData(): MovieDetailsData {
    return MovieDetailsData(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        belongsToCollection = belongsToCollection.toCollectionData(),
        budget = budget ?: 0,
        genres = genres?.toGenreData() ?: emptyList(),
        homepage = homepage.orEmpty(),
        id = id ?: 0,
        imdbId = imdbId.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = posterPath.orEmpty(),
        productionCompanies = productionCompanies?.toProductionCompanyData() ?: emptyList(),
        productionCountries = productionCountries?.toProductionCountryData() ?: emptyList(),
        releaseDate = releaseDate.orEmpty(),
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        spokenLanguages = spokenLanguages?.toSpokenLanguageData() ?: emptyList(),
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}

fun List<Genre>.toGenreData(): List<GenreData> {
    return safeMap {
        GenreData(
            id = it.id ?: 0,
            name = it.name.orEmpty()
        )
    }
}

fun List<ProductionCompany>.toProductionCompanyData(): List<ProductionCompanyData> {
    return safeMap {
        ProductionCompanyData(
            id = it.id ?: 0,
            logoPath = it.logoPath.orEmpty(),
            name = it.name.orEmpty(),
            originCountry = it.originCountry.orEmpty()
        )
    }
}

fun List<ProductionCountry>.toProductionCountryData(): List<ProductionCountryData> {
    return safeMap {
        ProductionCountryData(
            iso_3166_1 = it.iso_3166_1.orEmpty(),
            name = it.name.orEmpty()
        )
    }
}

fun List<SpokenLanguage>.toSpokenLanguageData(): List<SpokenLanguageData> {
    return safeMap {
        SpokenLanguageData(
            englishName = it.englishName.orEmpty(),
            iso_639_1 = it.iso_639_1.orEmpty(),
            name = it.name.orEmpty()
        )
    }
}

fun Collection?.toCollectionData(): CollectionData {
    return CollectionData(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        posterPath = this?.posterPath.orEmpty(),
        backdropPath = this?.backdropPath.orEmpty()
    )
}