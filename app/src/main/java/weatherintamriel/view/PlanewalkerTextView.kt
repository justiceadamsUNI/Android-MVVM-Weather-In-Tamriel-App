package weatherintamriel.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class PlanewalkerTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    init {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/Planewalker.otf")
        this.typeface = typeface
    }
}