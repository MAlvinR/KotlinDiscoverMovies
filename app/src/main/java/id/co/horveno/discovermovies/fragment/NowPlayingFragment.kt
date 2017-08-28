package id.co.horveno.discovermovies.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import id.co.horveno.discovermovies.EndPoint

import id.co.horveno.discovermovies.R

class NowPlayingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.now_playing_fragment, container, false)

        return view
    }

    private fun getNowPlayingData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(activity)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET, EndPoint.URL_UPCOMING, Response.Listener { s ->

        }, Response.ErrorListener { error: VolleyError? ->

        })
        requestQueue.add<String>(stringRequest)
    }
}
