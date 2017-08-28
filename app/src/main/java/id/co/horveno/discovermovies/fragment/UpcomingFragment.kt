package id.co.horveno.discovermovies.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.gson.Upcoming
import kotlinx.android.synthetic.main.upcoming_fragment.*

class UpcomingFragment : Fragment() {

    var upcomingGson:Upcoming? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val upcomingView = inflater!!.inflate(R.layout.upcoming_fragment, container, false)

        val linearLayoutManager = LinearLayoutManager(activity)

        recyclerUpcoming.layoutManager = linearLayoutManager
        getUpcomingData()
        return upcomingView
    }

    private fun getUpcomingData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(activity)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET, id.co.horveno.discovermovies.util.EndPoint.URL_NOWPLAYING, object:Response.Listener<String> {
            override fun onResponse(response: String?) {
                val gsonBuilder:GsonBuilder = GsonBuilder()
                val gson:Gson = gsonBuilder.create()

                upcomingGson = gson.fromJson(response, Upcoming::class.java)
            }


        }, object:Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("UpcomingFragment", error.toString())
            }

        })
                requestQueue.add<String>(stringRequest)
    }
}
