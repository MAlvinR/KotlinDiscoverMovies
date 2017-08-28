package id.co.horveno.discovermovies

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.Toolbar
import id.co.horveno.discovermovies.adapter.TabAdapter

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        tabHome.addTab(tabHome.newTab().setText("Now Playing"))
        tabHome.addTab(tabHome.newTab().setText("Upcoming"))

        var adapter = TabAdapter(supportFragmentManager)
        vPagerForTabs.adapter = adapter

        vPagerForTabs.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabHome))

        tabHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vPagerForTabs.setCurrentItem(tab?.position!!)
            }

        })
    }



    }





