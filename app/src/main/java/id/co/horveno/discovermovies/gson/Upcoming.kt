package id.co.horveno.discovermovies.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by ASUS on 28/08/2017.
 */

class Upcoming {

    @SerializedName("results")
    var data: List<UpcomingData>? = null

    inner class UpcomingData {
        @SerializedName("vote_count")
        var vote_count:String? = null

        @SerializedName("id")
        var idMovie:String? = null

        @SerializedName("vote_average")
        var vote_average:String? = null

        @SerializedName("title")
        var movieTitle:String? = null

        @SerializedName("popularity")
        var popularity:String? = null

        @SerializedName("poster_path")
        var poster_path:String? = null

    }

}