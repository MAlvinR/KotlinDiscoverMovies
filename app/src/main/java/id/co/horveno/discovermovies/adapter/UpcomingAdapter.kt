package id.co.horveno.discovermovies.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.activity.DetailActivity
import id.co.horveno.discovermovies.gson.NowPlaying
import id.co.horveno.discovermovies.gson.Upcoming
import id.co.horveno.discovermovies.util.EndPoint
import id.co.horveno.discovermovies.util.SquareLayout

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
                .load(EndPoint.IMAGE_URL_POSTER + upcomingData.poster_path)
                .placeholder(R.drawable.placeholder)
                .into(holder!!.movieThumb)

        holder.squareLayout.setOnClickListener({v ->
            val intent = Intent(mContext?.applicationContext, DetailActivity::class.java)
            intent.putExtra("id_movie", upcomingData.idMovie)
            intent.putExtra("title_movie", upcomingData.movieTitle)
            intent.putExtra("backdrop_movie", upcomingData.backdrop_path)
            intent.putExtra("overview_movie", upcomingData.overview)
            intent.putExtra("releasedate_movie", upcomingData.release_date)
            intent.putExtra("votesaverage_movie", upcomingData.vote_average)
            intent.putExtra("votecount_movie", upcomingData.vote_count)
            mContext!!.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UpcomingItemHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.upcoming_thumbnail, parent, false)

        return UpcomingItemHolder(inflater)
    }

    class UpcomingItemHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {
        var movieThumb = itemView?.findViewById(R.id.upcomingThumbnail) as ImageView
        var squareLayout = itemView?.findViewById(R.id.sq_Upcoming) as SquareLayout
    }

}
