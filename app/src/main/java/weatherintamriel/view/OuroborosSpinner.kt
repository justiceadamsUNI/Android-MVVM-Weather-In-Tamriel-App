package weatherintamriel.view

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import justiceadams.com.weatherintamriel.R

class OuroborosSpinner @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    init {
        background = resources.getDrawable(R.drawable.ouroboros)
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.infinite_spin) )
    }
}