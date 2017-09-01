package id.co.horveno.discovermovies.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.activity.DetailActivity
import id.co.horveno.discovermovies.gson.NowPlaying
import id.co.horveno.discovermovies.gson.Trailer
import id.co.horveno.discovermovies.util.EndPoint
import id.co.horveno.discovermovies.util.SquareLayout

/**
 * Created by ASUS on 31/08/2017.
 */

class TrailerAdapter: RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    var mTrailerData:List<Trailer.Data>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity, data:List<Trailer.Data>?) {
        this.mContext = c
        this.mTrailerData = data
    }

    override fun onBindViewHolder(holder: TrailerHolder?, position: Int) {
        val trailerData: Trailer.Data = mTrailerData!!.get(position)

        Picasso.with(mContext)
                .load("http://img.youtube.com/vi/${trailerData.key}/hqdefault.jpg")
                .into(holder!!.imgTrailer)

        holder.imgTrailer.setOnClickListener ({v ->
            watchYoutube(trailerData.key)
        })

    }

    override fun getItemCount(): Int {
        return mTrailerData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TrailerHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.trailer_item, parent, false)

        return TrailerHolder(inflater)
    }

    class TrailerHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imgTrailer = itemView?.findViewById(R.id.img_trailer) as ImageView
    }

    private fun watchYoutube(id:String?) {
        val appIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id))
        val webIntent: Intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id))

        try {
            mContext!!.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            mContext!!.startActivity(webIntent)
        }
    }

}
