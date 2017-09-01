package id.co.horveno.discovermovies.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by ASUS on 30/08/2017.
 */

class Detail {

    @SerializedName("backdrop_path")
    val backdrop_path:String? = null

    @SerializedName("budget")
    val budget:String? = null

    @SerializedName("genres")
    val genres:List<Genre>? = null

    class Genre {
        @SerializedName("id")
        val genreId:String? = null

        @SerializedName("name")
        val genre:String? = null
    }

    @SerializedName("homepage")
    val homepage_url:String? = null

    @SerializedName("original_title")
    val original_title:String? = null

    @SerializedName("overview")
    val overview:String? = null

    @SerializedName("release_date")
    val release_date:String? = null

    @SerializedName("runtime")
    val runtime:String? = null

    

}
