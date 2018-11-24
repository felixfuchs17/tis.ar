package ar.tis.tisar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.displaytext_view.view.*

class DisplayTextView(context: Context, attrs: AttributeSet? = null, defStyle: Int = -1)
    : FrameLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.displaytext_view, this)
    }

    fun setText(s: String){
        text.text = s.toString()
    }
}