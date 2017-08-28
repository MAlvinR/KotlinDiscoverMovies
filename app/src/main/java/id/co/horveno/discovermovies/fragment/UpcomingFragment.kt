package id.co.horveno.discovermovies.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.co.horveno.discovermovies.R

class UpcomingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val upcomingView = inflater!!.inflate(R.layout.upcoming_fragment, container, false)

        return upcomingView
    }
}
