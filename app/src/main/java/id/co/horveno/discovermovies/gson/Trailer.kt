package id.co.horveno.discovermovies.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by ASUS on 31/08/2017.
 */

class Trailer {
    @SerializedName("results")
    var trailer_results:List<Trailer.Data>? = null

    class Data {
        @SerializedName("id")
        var id: String? = null
        @SerializedName("key")
        var key: String? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("type")
        var type: String? = null
        @SerializedName("site")
        var site: String? = null
    }
}
