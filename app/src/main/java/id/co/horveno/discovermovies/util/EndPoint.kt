package id.co.horveno.discovermovies.util

import android.net.Uri
import java.net.URL

/**
 * Created by ASUS on 28/08/2017.
 */

object EndPoint {
    private val BASE_URL = "http://api.themoviedb.org/3/movie/"

    private val API_KEY = "?api_key=5bcd103535c907563275e5c79a7abd77"
    private val NOW_PLAYING = "now_playing"
    private val UPCOMING = "upcoming"

    val IMAGE_URL_POSTER = "http://image.tmdb.org/t/p/w185"

    val IMAGE_URL_BACKDROP = "http://image.tmdb.org/t/p/w780"

    val URL_NOWPLAYING = BASE_URL + NOW_PLAYING + API_KEY
    val URL_UPCOMING = BASE_URL + UPCOMING + API_KEY

}
