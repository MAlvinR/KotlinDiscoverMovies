package id.co.horveno.discovermovies.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import id.co.horveno.discovermovies.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val ptSans: Typeface = Typeface.createFromAsset(assets, "fonts/ptsans_regular.ttf")

        header_title.typeface = ptSans
        votes_average.typeface = ptSans
        votes_result.typeface = ptSans
        release_year.typeface = ptSans
        storyline_title.typeface = ptSans
        text_overview.typeface = ptSans

        initCollapsingToolbar()

        rb_votes.rating = 4.5f
    }

    private fun initCollapsingToolbar() {
        toolbar_layout.title = ""
        app_bar.addOnOffsetChangedListener(object:AppBarLayout.OnOffsetChangedListener {

            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = app_bar.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout.title = getString(R.string.dummy_title)
                    isShow = true
                } else if (isShow) {
                    toolbar_layout.title = ""
                    isShow = false
                }
            }

        })

    }
}
