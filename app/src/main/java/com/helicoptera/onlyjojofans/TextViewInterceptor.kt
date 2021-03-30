package com.helicoptera.onlyjojofans

import android.graphics.Typeface
import android.util.TypedValue
import android.widget.TextView
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor

class TextViewInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): InflateResult {
        val result = chain.proceed(chain.request())
        if (result.view is TextView) {

            val textView = result.view as TextView
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, ApplicationSettings.fontSize.toFloat())
            textView.typeface = Typeface.create(ApplicationSettings.fontName, Typeface.NORMAL)

        }
        return result
    }
}