package id.co.horveno.discovermovies.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.gson.NowPlaying
import kotlinx.android.synthetic.main.now_playing_fragment.*
import id.co.horveno.discovermovies.util.EndPoint
import id.co.horveno.discovermovies.adapter.NowPlayingAdapter

class NowPlayingFragment : Fragment() {

    var nowPlayingGson:NowPlaying? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.now_playing_fragment, container, false)


        getNowPlayingData()
        return view
    }

    private fun getNowPlayingData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(activity)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET, EndPoint.URL_UPCOMING, object:Response.Listener<String> {

            override fun onResponse(response: String?) {

                var gsonBuilder:GsonBuilder = GsonBuilder()
                var gson:Gson = gsonBuilder.create()

                nowPlayingGson = gson.fromJson(response, NowPlaying::class.java)

                val adapter = NowPlayingAdapter(activity, nowPlayingGson!!.data)
                recyclerNowPlaying.adapter = adapter
                var gridLayoutManager = GridLayoutManager(activity, 2)

                recyclerNowPlaying.layoutManager = gridLayoutManager
                /*Toast.makeText(activity, response, Toast.LENGTH_LONG).show()*/


            }

        }, object:Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("NowPlayingFragment", error.toString())
            }

        })
        requestQueue.add<String>(stringRequest)
    }
}
