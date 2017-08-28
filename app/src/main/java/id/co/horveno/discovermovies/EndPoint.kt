package id.co.horveno.discovermovies

/**
 * Created by ASUS on 28/08/2017.
 */

class EndPoint {
    private val BASE_URL = "http://api.themoviedb.org/3/movie/"

    private val API_KEY = "?api_key=5bcd103535c907563275e5c79a7abd77"
    private val NOW_PLAYING = "now_playing"
    private val UPCOMING = "upcoming"

    val URL_NOWPLAYING = BASE_URL + NOW_PLAYING + API_KEY
    val URL_UPCOMING = BASE_URL + UPCOMING + API_KEY

}
