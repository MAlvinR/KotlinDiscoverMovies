package id.co.horveno.discovermovies.adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.gson.NowPlaying

/**
 * Created by ASUS on 28/08/2017.
 */

class NowPlayingAdapter: RecyclerView.Adapter<NowPlayingAdapter.NowPlayingItemHolder> {

    var mMovieData:List<NowPlaying.NowPlayingData>? = null
    var mContext:Context? = null

    constructor(c:FragmentActivity, data:List<NowPlaying.NowPlayingData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onBindViewHolder(holder: NowPlayingItemHolder?, position: Int) {
        val IMAGE_URL:String = "http://image.tmdb.org/t/p/w185"

        Picasso.with(mContext)
                .load(IMAGE_URL + mMovieData.poster)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NowPlayingItemHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.movie_thumbnail, parent, false)

        return NowPlayingItemHolder(inflater)
    }

    class NowPlayingItemHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {
            var movieThumb = itemView?.findViewById(R.id.thumbnail) as ImageView
    }

}
