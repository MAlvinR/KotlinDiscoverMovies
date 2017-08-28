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
import id.co.horveno.discovermovies.gson.Upcoming
import id.co.horveno.discovermovies.util.EndPoint

/**
 * Created by ASUS on 28/08/2017.
 */

class UpcomingAdapter: RecyclerView.Adapter<UpcomingAdapter.UpcomingItemHolder> {

    var mMovieData:List<Upcoming.UpcomingData>? = null
    var mContext:Context? = null

    constructor(c:FragmentActivity, data:List<Upcoming.UpcomingData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onBindViewHolder(holder: UpcomingItemHolder?, position: Int) {

        val upcomingData : Upcoming.UpcomingData = mMovieData!!.get(position)

        Picasso.with(mContext)
                .load(EndPoint.IMAGE_URL + upcomingData.poster_path)
                .into(holder!!.movieThumb)
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UpcomingItemHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.upcoming_thumbnail, parent, false)

        return UpcomingItemHolder(inflater)
    }

    class UpcomingItemHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {
        var movieThumb = itemView?.findViewById(R.id.nowPlayingThumbnail) as ImageView
    }

}
