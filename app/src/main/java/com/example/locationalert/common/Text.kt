package com.example.locationalert.common

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class Text @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    type:Int =1
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        // Apply any customizations or initialization logic here
        if(type==1)
        setTextColor(Color.RED)
        textSize = 18f
    }
}
