package id.co.horveno.discovermovies.util

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 * Created by ASUS on 28/08/2017.
 */

class SquareLayout : RelativeLayout {

    constructor(context:Context) : super(context)
    constructor(context: Context,attr:AttributeSet) : super(context, attr)
    constructor(context: Context,attr:AttributeSet,defStyleAttr:Int) : super(context, attr, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context,attr:AttributeSet,defStyleAttr:Int,defStyleRes:Int) : super(context, attr, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}
