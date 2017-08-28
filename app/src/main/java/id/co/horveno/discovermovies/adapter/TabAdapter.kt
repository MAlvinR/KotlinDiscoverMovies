package id.co.horveno.discovermovies.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.co.horveno.discovermovies.fragment.NowPlayingFragment
import id.co.horveno.discovermovies.fragment.UpcomingFragment

/**
 * Created by ASUS on 28/08/2017.
 */

class TabAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return NowPlayingFragment()
        } else {
            return UpcomingFragment()
        }
    }

    override fun getCount(): Int {
        return 2;
    }

}
