package weatherintamriel.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import justiceadams.com.weatherintamriel.R


class WeatherListRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(WeatherListDivider(context))
    }
}

private class WeatherListDivider(context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable = context.resources.getDrawable(R.drawable.divider)

    //Taken from
    // https://stackoverflow.com/questions/31242812/how-can-a-divider-line-be-added-in-an-android-recyclerview
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}