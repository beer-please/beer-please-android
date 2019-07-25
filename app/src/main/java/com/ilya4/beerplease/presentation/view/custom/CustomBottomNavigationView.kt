package com.ilya4.beerplease.presentation.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomBottomNavigationView @JvmOverloads constructor(context: Context,
                                                           attrs: AttributeSet? = null,
                                                           defStyleAttr: Int = 0):
    BottomNavigationView(context, attrs, defStyleAttr) {

    private val path: Path = Path()
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var radius = 0

    // Navigation bar bounds (width & height)
    private var navigationBarWidth: Int = 0
    private var navigationBarHeight: Int = 0

    private val firstPoint = Point()
    private val middlePoint = Point()
    private val lastPoint = Point()

    init {
        with(paint) {
            style = Paint.Style.FILL_AND_STROKE
            color = Color.WHITE
        }
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        navigationBarWidth = width
        navigationBarHeight = height

        radius = (height * 0.37f).toInt()

        firstPoint.set(0,0)
        lastPoint.set(navigationBarWidth, 0)
        middlePoint.set(navigationBarWidth / 2, radius * 2)

        path.apply {
            reset()
            moveTo(
                firstPoint.x.toFloat(),
                firstPoint.y.toFloat()
                )
            quadTo(
                middlePoint.x.toFloat(),
                middlePoint.y.toFloat(),
                lastPoint.x.toFloat(),
                lastPoint.y.toFloat()
            )

            lineTo(navigationBarWidth.toFloat(), navigationBarHeight.toFloat())
            lineTo(0f, navigationBarHeight.toFloat())
            close()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }
}