package id.co.horveno.discovermovies.util

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

/**
 * Created by ASUS on 01/09/2017.
 */

object CustomFont {

    fun PtSansRegular(res:TextView, context:Context) {
        val ptSansRegular: Typeface = Typeface.createFromAsset(context.assets, "fonts/ptsans_regular.ttf")
        res.typeface = ptSansRegular

    }

    fun PtSansBold(res: TextView, context: Context) {
        val ptSansBold: Typeface = Typeface.createFromAsset(context.assets, "fonts/ptsans_bold.ttf")
        res.typeface = ptSansBold
    }

}
