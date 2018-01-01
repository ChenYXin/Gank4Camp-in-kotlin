package com.donkor.gank4camp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.Scroller
import android.util.AttributeSet


/**
 * Created by donkor on 2017/12/31.
 */
class ScrollChooseView constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var titles: Array<String>? = null
    private val paint: Paint = Paint()
    private val textBound: Rect = Rect()
    private var downX: Int = 0

    private val mScroller: Scroller
    private var lastScrollX = 0
    private var isScroll = false

    private var isClick = false
    //    private var onScrollEndListener: OnScrollEndListener? = null
    private var picIds: IntArray? = null


    private val currentPosition: Int
        get() {
            var position = 1

            if (scrollX > measuredWidth / 6 * 1 * -3 && scrollX <= measuredWidth / 6 * 1 * -1) {
                position = 0
            } else if (scrollX > measuredWidth / 6 * 1 * -1 && scrollX <= measuredWidth / 6 * 1 * 1) {
                position = 1
            } else if (scrollX > measuredWidth / 6 * 1 * 1 && scrollX <= measuredWidth / 6 * 1 * 3) {
                position = 2
            } else if (scrollX > measuredWidth / 6 * 1 * 3 && scrollX <= measuredWidth / 6 * 1 * 5) {
                position = 3
            } else if (scrollX > measuredWidth / 6 * 1 * 5 && scrollX <= measuredWidth / 6 * 1 * 7) {
                position = 4
            } else if (scrollX > measuredWidth / 6 * 1 * 7 && scrollX <= measuredWidth / 6 * 1 * 9) {
                position = 5
            } else if (scrollX > measuredWidth / 6 * 1 * 9 && scrollX <= measuredWidth / 6 * 1 * 11) {
                position = 6
            } else if (scrollX > measuredWidth / 6 * 1 * 11 && scrollX <= measuredWidth / 6 * 1 * 13) {
                position = 7
            } else if (scrollX > measuredWidth / 6 * 1 * 13 && scrollX <= measuredWidth / 6 * 1 * 15) {
                position = 8
            }

            return position

        }

    companion object {
        fun sp2px(context: Context, spVal: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    spVal, context.resources.displayMetrics)
        }

        fun dp2px(context: Context, dpVal: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dpVal, context.resources.displayMetrics).toInt()
        }
    }

    init {
        val mContext: Context = context
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.textSize = sp2px(mContext, 10f)
        mScroller = Scroller(context)

    }

   public fun setPicIds(picIds: IntArray) {
        this.picIds = picIds
    }

    public  fun setTitles(titles: Array<String>) {
        this.titles = titles
        invalidate()
    }

//    fun setOnScrollEndListener(onScrollEndListener: OnScrollEndListener) {
//        this.onScrollEndListener = onScrollEndListener
//    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (titles!!.size <= 1) {
            return super.onTouchEvent(event)
        }
        val x = event.x.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isClick = true
                downX = x
            }
            MotionEvent.ACTION_MOVE -> {
                isClick = false
                val offset = x - downX
                if (scrollX < -measuredWidth / 3.0 || scrollX > measuredWidth / 3.0 * titles!!.size - measuredWidth + measuredWidth / 3.0) {
                    return super.onTouchEvent(event)
                } else {
                    scrollX(lastScrollX - offset, true)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (isClick) {
                    return true
                }
                if (scrollX < -measuredWidth / 3.0) {
                    scrollX((-measuredWidth / 3.0).toInt(), true)
                }
                if (scrollX > measuredWidth / 3.0 * titles!!.size - measuredWidth + measuredWidth / 3.0) {
                    scrollX((measuredWidth / 3.0 * titles!!.size - measuredWidth + measuredWidth / 3.0).toInt(), true)
                }

                scrollX(measuredWidth / 3 * (currentPosition - 1), false)
            }
        }
        return true
    }

    private fun scrollX(endX: Int, b: Boolean) {
        if (b) {
            scrollTo(endX, 0)
        } else {
            isScroll = true
            lastScrollX = endX
            smoothScrollTo(endX, 0)
        }
    }

    private fun smoothScrollTo(destX: Int, destY: Int) {
        mScroller.startScroll(scrollX, 0, destX - scrollX, 0, 200)
        invalidate()
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        } else {
            if (isScroll) {
                scrollX = lastScrollX
                isScroll = false
//                if (onScrollEndListener != null) {
//                    onScrollEndListener!!.currentPosition(currentPosition)
//                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)

        setMeasuredDimension(if (widthMode == View.MeasureSpec.EXACTLY) widthSize else widthSize, if (heightMode == View.MeasureSpec.EXACTLY) heightSize else dp2px(context, 100f))

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (titles == null) {
            return
        }
        drawText(canvas)

    }

    private fun drawText(canvas: Canvas) {
        for (i in titles!!.indices) {
            if (currentPosition == i) {
                paint.textSize = sp2px(context, 20f)
                paint.getTextBounds(titles!![i], 0, titles!![i].length, textBound)
                canvas.drawText(titles!![i], (measuredWidth / 6 * (2 * i + 1) - textBound.width() / 2).toFloat(), (measuredHeight / 2 + textBound.height() / 2).toFloat(), paint)
                paint.strokeWidth = 3f
                canvas.drawLine((measuredWidth / 6 * (2 * i + 1) - textBound.width() / 2 - 10).toFloat(), (measuredHeight / 2 + textBound.height()).toFloat(),
                        (measuredWidth / 6 * (2 * i + 1) + textBound.width() / 2 + 10).toFloat(), (measuredHeight / 2 + textBound.height()).toFloat(), paint)
            } else {
                paint.textSize = sp2px(context, 10f)
                paint.getTextBounds(titles!![i], 0, titles!![i].length, textBound)
                canvas.drawText(titles!![i], (measuredWidth / 6 * (2 * i + 1) - textBound.width() / 2).toFloat(), (measuredHeight / 2 + textBound.height() / 2).toFloat(), paint)
            }
            if (currentPosition >= picIds!!.size) {
                return
            }
            setBackgroundResource(picIds!![currentPosition])
        }
    }

//    interface OnScrollEndListener {
//        fun currentPosition(position: Int)
//    }

}