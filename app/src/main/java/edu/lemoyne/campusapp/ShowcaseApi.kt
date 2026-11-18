package edu.lemoyne.campusapp

import retrofit2.http.GET
import retrofit2.http.Header
import kotlin.io.encoding.Base64

interface ShowcaseApi {
    @GET(value="/rest/v1/showcase_items")
    suspend fun getShowcaseItems(
        @Header(value="apikey") apiKey: String
    ): List<ShowcaseItem>
}

object ShowCaseService {
    const val API_KEY="sb_publishable_iNnjqrnTECp098Fb0qDn0g_4Ey3YJmD"
    const val BASE_URL="https://clxxzixkmmqngufzwgwe.supabase.co"

    val api: ShowcaseApi = Retrofit.builder()
        .baseUrl(BASE_URL)
        .addconverterFactory(GSonConverFactory.create())
        .build()
        .create(ShowcaseApi::class.java)
}