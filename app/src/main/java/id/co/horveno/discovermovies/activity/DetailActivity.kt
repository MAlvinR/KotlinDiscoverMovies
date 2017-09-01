package id.co.horveno.discovermovies.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.text.format.DateUtils
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
import android.text.format.Time
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import id.co.horveno.discovermovies.adapter.TrailerAdapter
import id.co.horveno.discovermovies.gson.Trailer
import id.co.horveno.discovermovies.util.CustomFont
import id.co.horveno.discovermovies.util.SquareLayout
import kotlinx.android.synthetic.main.movie_about_description.*
import kotlinx.android.synthetic.main.trailer_item.*


class DetailActivity : AppCompatActivity() {

    var detailGson: Detail? = null
    var trailerGson: Trailer? = null

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
        release_year.text = timeSetUp(releasedateMovie)
        votes_average.text = votesaverageMovie.toString()

        Picasso.with(applicationContext)
                .load(EndPoint.IMAGE_URL_BACKDROP + backdropMovie)
                .into(header_thumbnail)

        val layoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        listTrailer.layoutManager = layoutManager

        votesCount(votecountMovie)
        initCollapsingToolbar(titleMovie)
        fontSetup()
        loadDetail(idMovie)
        loadTrailer(idMovie)

        rb_votes.rating = votesaverageMovie.toFloat() / 2
    }

    private fun fontSetup() {

        CustomFont.PtSansRegular(header_title, this@DetailActivity)
        CustomFont.PtSansRegular(votes_average, this@DetailActivity)
        CustomFont.PtSansRegular(votes_result, this@DetailActivity)
        CustomFont.PtSansBold(release_year, this@DetailActivity)
        CustomFont.PtSansRegular(storyline_title, this@DetailActivity)
        CustomFont.PtSansRegular(text_overview, this@DetailActivity)
        CustomFont.PtSansRegular(trailers_title, this@DetailActivity)

        /*About Film*/
        CustomFont.PtSansRegular(txt_about, this@DetailActivity)
        CustomFont.PtSansRegular(title_original, this@DetailActivity)
        CustomFont.PtSansRegular(title_type, this@DetailActivity)
        CustomFont.PtSansRegular(title_production, this@DetailActivity)
        CustomFont.PtSansRegular(title_premiere, this@DetailActivity)
        CustomFont.PtSansRegular(title_desc, this@DetailActivity)

        CustomFont.PtSansRegular(content_original, this@DetailActivity)
        CustomFont.PtSansRegular(content_type, this@DetailActivity)
        CustomFont.PtSansRegular(content_production, this@DetailActivity)
        CustomFont.PtSansRegular(content_premiere, this@DetailActivity)
        CustomFont.PtSansRegular(content_desc, this@DetailActivity)
    }

    private fun votesCount(voteCount: Int) {
        if (voteCount >= 3) {
            votes_result.text = voteCount.toString() + " VOTES"
        } else {
            votes_result.text = voteCount.toString() + " VOTE"
        }
    }

    private fun initCollapsingToolbar(titleMovie: String) {
        toolbar_layout_detail.title = ""
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

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

    private fun loadDetail(idMovie: Int) {

        val URL_DETAIL: String = "http://api.themoviedb.org/3/movie/${idMovie}?api_key=5bcd103535c907563275e5c79a7abd77"

        val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET, URL_DETAIL, object : Response.Listener<String> {

            override fun onResponse(response: String?) {

                var gsonBuilder: GsonBuilder = GsonBuilder()
                var gson: Gson = gsonBuilder.create()
                detailGson = gson.fromJson(response, Detail::class.java)

                runtime_text.text = detailGson!!.runtime + " Minutes"


            }

        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("DetailActivity", error.toString())
            }

        })
        requestQueue.add(stringRequest)
    }

    private fun loadTrailer(idMovie: Int) {
        pg_trailers.visibility = GONE

        val URL_TRAILER: String = "https://api.themoviedb.org/3/movie/${idMovie}/videos?api_key=5bcd103535c907563275e5c79a7abd77"

        val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET, URL_TRAILER, object : Response.Listener<String> {

            override fun onResponse(response: String?) {

                if (response != null) {
                    Log.i("TrailerResponse", response)
                    var gsonBuilder: GsonBuilder = GsonBuilder()
                    var gson: Gson = gsonBuilder.create()

                    trailerGson = gson.fromJson(response, Trailer::class.java)
                    /*displayTrailer(trailerGson!!.trailer_results)*/
                    val adapter: TrailerAdapter = TrailerAdapter(this@DetailActivity, trailerGson!!.trailer_results)
                    listTrailer.adapter = adapter



                } else {
                    Toast.makeText(this@DetailActivity, "No Data!", Toast.LENGTH_SHORT).show()

                }
                pg_trailers.visibility = GONE

            }

        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("DetailActivity", error.toString())
                pg_trailers.visibility = GONE
            }

        })
        requestQueue.add(stringRequest)
    }

    /*private fun displayTrailer(trailerDatas: List<Trailer.Data>?) {
        view_trailers.removeAllViews()

        for (i in 0..trailerDatas!!.size - 1) {
            val trailerData: Trailer.Data = trailerDatas.get(i)
            val view: View? = LayoutInflater.from(applicationContext).inflate(R.layout.trailer_item, view_trailers, false)

            val gambar_trailer = view?.findViewById(R.id.img_trailer) as ImageView
*//*
            var judul_trailer = view?.findViewById(R.id.title_trailer) as TextView
*//*
            var judul_trailer = view?.findViewById(R.id.title_trailer) as TextView

            Toast.makeText(this@DetailActivity, "TrailerName: " + trailerData.name, Toast.LENGTH_LONG).show()
            judul_trailer.text = trailerData.name
            *//*if (trailerData.site.equals("youtube")) {
                Picasso.with(applicationContext)
                        .load("http://img.youtube.com/vi/${trailerData.key}/default.jpg")
                        .into(gambar_trailer)
            }*//*
        }
    }*/

    private fun timeSetUp(releasedateMovie: String): String {
        val time = Time()
        time.parse3339(releasedateMovie)
        return DateUtils.getRelativeTimeSpanString(time.toMillis(false),
                System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_ALL).toString()


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
