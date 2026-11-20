package edu.lemoyne.campusapp

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.Retrofit

interface ShowcaseAPI {
    @GET("/rest/v1/showcase_items")
    suspend fun getShowcaseItems(
        @Header("apikey") apiKey: String
    ): List<ShowcaseItem>
}

object ShowcaseService {
    const val API_KEY="sb_publishable_iNnjqrnTECp098Fb0qDn0g_4Ey3YJmD"
    const val BASE_URL="https://clxxzixkmmqngufzwgwe.supabase.co"

    val api: ShowcaseAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ShowcaseAPI::class.java)
}