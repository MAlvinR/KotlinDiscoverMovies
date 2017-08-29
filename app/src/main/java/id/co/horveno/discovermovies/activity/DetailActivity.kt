package id.co.horveno.discovermovies.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.squareup.picasso.Picasso

import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.util.EndPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var titleMovie = intent.getStringExtra("title_movie")
        var backdropMovie = intent.getStringExtra("backdrop_movie")
        var overviewMovie = intent.getStringExtra("overview_movie")
        var releasedateMovie = intent.getStringExtra("releasedate_movie")
        var votesaverageMovie = intent.getDoubleExtra("votesaverage_movie", 0.00)

        header_title.text = titleMovie
        text_overview.text = overviewMovie
        release_year.text = releasedateMovie
        votes_average.text = votesaverageMovie.toString()

        Picasso.with(applicationContext)
                .load(EndPoint.IMAGE_URL_BACKDROP + backdropMovie)
                .into(header_thumbnail)



        initCollapsingToolbar(titleMovie)
        fontSetup()

        rb_votes.rating = 4.5f
    }

    private fun fontSetup() {
        val ptSansRegular: Typeface = Typeface.createFromAsset(assets, "fonts/ptsans_regular.ttf")
        val ptSansBold: Typeface = Typeface.createFromAsset(assets, "fonts/ptsans_bold.ttf")

        header_title.typeface = ptSansRegular
        votes_average.typeface = ptSansRegular
        votes_result.typeface = ptSansRegular
        release_year.typeface = ptSansBold
        storyline_title.typeface = ptSansRegular
        text_overview.typeface = ptSansRegular
    }

    private fun initCollapsingToolbar(titleMovie:String) {
        toolbar_layout_detail.title = ""
        app_bar.addOnOffsetChangedListener(object:AppBarLayout.OnOffsetChangedListener {

            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = app_bar.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout_detail.title = titleMovie
                    isShow = true
                } else if (isShow) {
                    toolbar_layout_detail.title = ""
                    isShow = false
                }
            }

        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
