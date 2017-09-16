package id.co.horveno.discovermovies.util

import android.net.Uri
import java.net.URL

/**
 * Created by ASUS on 28/08/2017.
 */

object EndPoint {
    private val BASE_URL = "http://api.themoviedb.org/3/movie/"

    private val PATH_API_KEY = "?api_key="

    private val NOW_PLAYING = "now_playing"
    private val UPCOMING = "upcoming"

    val IMAGE_URL_POSTER = "http://image.tmdb.org/t/p/w185"

    val IMAGE_URL_BACKDROP = "http://image.tmdb.org/t/p/w780"

    val URL_NOWPLAYING = BASE_URL + NOW_PLAYING + PATH_API_KEY + Constant.API_KEY
    val URL_UPCOMING = BASE_URL + UPCOMING + PATH_API_KEY + Constant.API_KEY

}
