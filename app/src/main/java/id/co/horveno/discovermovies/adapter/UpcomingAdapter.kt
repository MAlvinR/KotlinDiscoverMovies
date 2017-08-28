package id.co.horveno.discovermovies.adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import id.co.horveno.discovermovies.gson.NowPlaying
import id.co.horveno.discovermovies.gson.Upcoming

/**
 * Created by ASUS on 28/08/2017.
 */

class UpcomingAdapter: RecyclerView.Adapter<UpcomingAdapter.NowPlayingItemHolder> {

    var mMovieData:List<Upcoming.UpcomingData>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity, data:List<Upcoming.UpcomingData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onBindViewHolder(holder: NowPlayingItemHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NowPlayingItemHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class NowPlayingItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

}
