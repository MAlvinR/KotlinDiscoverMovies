package id.co.horveno.discovermovies.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import id.co.horveno.discovermovies.R
import id.co.horveno.discovermovies.gson.Detail
import id.co.horveno.discovermovies.util.EndPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    var detailGson:Detail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var idMovie = intent.getIntExtra("id_movie", 0)
        var titleMovie = intent.getStringExtra("title_movie")
        var backdropMovie = intent.getStringExtra("backdrop_movie")
        var overviewMovie = intent.getStringExtra("overview_movie")
        var releasedateMovie = intent.getStringExtra("releasedate_movie")
        var votesaverageMovie = intent.getDoubleExtra("votesaverage_movie", 0.00)
        var votecountMovie = intent.getIntExtra("votecount_movie", 0)

        header_title.text = titleMovie
        text_overview.text = overviewMovie
        release_year.text = releasedateMovie
        votes_average.text = votesaverageMovie.toString()


        Picasso.with(applicationContext)
                .load(EndPoint.IMAGE_URL_BACKDROP + backdropMovie)
                .into(header_thumbnail)


        votesCount(votecountMovie)
        initCollapsingToolbar(titleMovie)
        fontSetup()
        loadDetail(idMovie)

        rb_votes.rating = votesaverageMovie.toFloat() / 2
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

    private fun votesCount(voteCount:Int) {
        if (voteCount >= 3) {
            votes_result.text = voteCount.toString() + " VOTES"
        } else {
            votes_result.text = voteCount.toString() + " VOTE"
        }
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

    private fun loadDetail(idMovie:Int) {

        val URL_DETAIL:String = "http://api.themoviedb.org/3/movie/${idMovie}?api_key=5bcd103535c907563275e5c79a7abd77"

        val requestQueue:RequestQueue = Volley.newRequestQueue(applicationContext)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET, URL_DETAIL, object:Response.Listener<String> {

            override fun onResponse(response: String?) {

                var gsonBuilder:GsonBuilder = GsonBuilder()
                var gson:Gson = gsonBuilder.create()

                detailGson = gson.fromJson(response, Detail::class.java)

                runtime_text.text = detailGson!!.runtime + " Minutes"
            }

        }, object:Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("DetailActivity", error.toString())
            }

        })
        requestQueue.add(stringRequest)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
