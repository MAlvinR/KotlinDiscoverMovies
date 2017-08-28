package id.co.horveno.discovermovies.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by ASUS on 28/08/2017.
 */

class NowPlaying {

    @SerializedName("results")
    var data: List<NowPlayingData>? = null

    inner class NowPlayingData {
        @SerializedName("vote_count")
        val vote_count:String? = null

        @SerializedName("id")
        val idMovie:String? = null

        @SerializedName("vote_average")
        val vote_average:String? = null

        @SerializedName("title")
        val movieTitle:String? = null

        @SerializedName("popularity")
        val popularity:Float? = null

        @SerializedName("poster_path")
        val poster_path:String? = null

    }

}
