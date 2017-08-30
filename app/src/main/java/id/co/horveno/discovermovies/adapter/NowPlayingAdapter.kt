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
import id.co.horveno.discovermovies.util.EndPoint
import id.co.horveno.discovermovies.util.SquareLayout

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

        val nowPlayingData:NowPlaying.NowPlayingData = mMovieData!!.get(position)

        Picasso.with(mContext)
                .load(EndPoint.IMAGE_URL_POSTER + nowPlayingData.poster_path)
                .into(holder!!.movieThumb)

        holder.squareLayout.setOnClickListener({v ->
            /*Toast.makeText(mContext, "" + nowPlayingData.movieTitle, Toast.LENGTH_SHORT).show()*/
            val intent = Intent(mContext?.applicationContext, DetailActivity::class.java)
            intent.putExtra("id_movie", nowPlayingData.idMovie)
            Toast.makeText(mContext, "ID: " + nowPlayingData.idMovie, Toast.LENGTH_SHORT).show()
            intent.putExtra("title_movie", nowPlayingData.movieTitle)
            intent.putExtra("backdrop_movie", nowPlayingData.backdrop_path)
            intent.putExtra("overview_movie", nowPlayingData.overview)
            intent.putExtra("releasedate_movie", nowPlayingData.release_date)
            intent.putExtra("votesaverage_movie", nowPlayingData.vote_average)
            intent.putExtra("votecount_movie", nowPlayingData.vote_count)
            mContext!!.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NowPlayingItemHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.nowplaying_thumbnail, parent, false)

        return NowPlayingItemHolder(inflater)
    }

    class NowPlayingItemHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {
            var movieThumb = itemView?.findViewById(R.id.nowPlayingThumbnail) as ImageView
            var squareLayout = itemView?.findViewById(R.id.sq_NowPlaying) as SquareLayout
    }

}
