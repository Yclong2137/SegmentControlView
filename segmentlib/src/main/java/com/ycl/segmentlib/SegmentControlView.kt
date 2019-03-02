package com.ycl.segmentlib

import android.content.Context
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup

/**
 * 分段控件
 */
class SegmentControlView : RadioGroup {

    private var mContext: Context? = null
    private var segmentStrokeWidth = dp2px(1f)
    private var segmentUnSelectedStrokeColor = Color.WHITE
    private var segmentSelectedStrokeColor = Color.BLUE
    private var segmentCornerRadius = dp2px(8f).toFloat()

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SegmentControlView, 0, 0)
        segmentStrokeWidth =
            typedArray.getDimensionPixelSize(R.styleable.SegmentControlView_segment_strokeWidth, segmentStrokeWidth)

        segmentSelectedStrokeColor =
            typedArray.getColor(R.styleable.SegmentControlView_segment_selectedStrokeColor, segmentSelectedStrokeColor)
        segmentUnSelectedStrokeColor =
            typedArray.getColor(
                R.styleable.SegmentControlView_segment_unSelectedStrokeColor,
                segmentUnSelectedStrokeColor
            )
        segmentCornerRadius =
            typedArray.getDimension(R.styleable.SegmentControlView_segment_cornerRadius, segmentCornerRadius)
        init(context)
    }

    private fun init(context: Context) {
        //  if (childCount < 1) throw IllegalArgumentException("childCount < 1")
        mContext = context
        val groupGradientDrawable = GradientDrawable()
        groupGradientDrawable.cornerRadius = segmentCornerRadius
        groupGradientDrawable.setStroke(segmentStrokeWidth, segmentSelectedStrokeColor)
        this.setBackgroundDrawable(groupGradientDrawable)
        this.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        val dividerDrawable = GradientDrawable()
        dividerDrawable.setColor(segmentSelectedStrokeColor)
        this.dividerPadding = 0
        this.post {
            if (orientation == RadioGroup.HORIZONTAL) {
                dividerDrawable.setSize(segmentStrokeWidth, this.measuredHeight)
            } else if (orientation == RadioGroup.VERTICAL) {
                dividerDrawable.setSize(this.measuredHeight, segmentStrokeWidth)
            }
            this.dividerDrawable = dividerDrawable
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("SegmentControlView.onMeasure")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        println("SegmentControlView.onLayout")
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount < 1) throw IllegalArgumentException("childCount cannot < 1")
        for (i in 0 until childCount) {
            val child = this.getChildAt(i)
            val stateListDrawable = StateListDrawable()
            val segmentSelectedDrawable = GradientDrawable()
            val segmentUnSelectedDrawable = GradientDrawable()
            val layoutParams = child.layoutParams
            when (i) {
                0 -> {
                    if (child is RadioButton) {
                        child.isChecked = true
                    }
                    if (orientation == RadioGroup.HORIZONTAL) {
                        if (layoutParams is MarginLayoutParams) {
                            layoutParams.bottomMargin = segmentStrokeWidth
                            layoutParams.topMargin = segmentStrokeWidth
                            layoutParams.leftMargin = segmentStrokeWidth
                        }
                        segmentSelectedDrawable.cornerRadii = floatArrayOf(
                            segmentCornerRadius,
                            segmentCornerRadius,
                            0f,
                            0f,
                            0f,
                            0f,
                            segmentCornerRadius,
                            segmentCornerRadius
                        )
                        segmentSelectedDrawable.setColor(Color.RED)
                        segmentUnSelectedDrawable.cornerRadii = floatArrayOf(
                            segmentCornerRadius,
                            segmentCornerRadius,
                            0f,
                            0f,
                            0f,
                            0f,
                            segmentCornerRadius,
                            segmentCornerRadius
                        )
                        segmentUnSelectedDrawable.setColor(segmentUnSelectedStrokeColor)
                    } else if (orientation == RadioGroup.VERTICAL) {
                        if (layoutParams is MarginLayoutParams) {
                            layoutParams.topMargin = segmentStrokeWidth
                            layoutParams.leftMargin = segmentStrokeWidth
                            layoutParams.rightMargin = segmentStrokeWidth
                        }
                        segmentSelectedDrawable.cornerRadii = floatArrayOf(
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            0f,
                            0f,
                            0f,
                            0f
                        )
                        segmentSelectedDrawable.setColor(Color.RED)
                        segmentUnSelectedDrawable.cornerRadii = floatArrayOf(
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            0f,
                            0f,
                            0f,
                            0f
                        )
                        segmentUnSelectedDrawable.setColor(segmentUnSelectedStrokeColor)
                    }


                }
                childCount - 1 -> {
                    if (orientation == RadioGroup.HORIZONTAL) {
                        if (layoutParams is MarginLayoutParams) {
                            layoutParams.bottomMargin = segmentStrokeWidth
                            layoutParams.topMargin = segmentStrokeWidth
                            layoutParams.rightMargin = segmentStrokeWidth
                        }
                        segmentSelectedDrawable.cornerRadii = floatArrayOf(
                            0f,
                            0f,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            0f,
                            0f
                        )
                        segmentSelectedDrawable.setColor(RED)
                        segmentUnSelectedDrawable.cornerRadii = floatArrayOf(
                            0f,
                            0f,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            0f,
                            0f
                        )
                        segmentUnSelectedDrawable.setColor(segmentUnSelectedStrokeColor)
                    } else if (orientation == RadioGroup.VERTICAL) {
                        if (layoutParams is MarginLayoutParams) {
                            layoutParams.bottomMargin = segmentStrokeWidth
                            layoutParams.rightMargin = segmentStrokeWidth
                            layoutParams.leftMargin = segmentStrokeWidth
                        }
                        segmentSelectedDrawable.cornerRadii = floatArrayOf(
                            0f,
                            0f,
                            0f,
                            0f,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius
                        )
                        segmentSelectedDrawable.setColor(RED)
                        segmentUnSelectedDrawable.cornerRadii = floatArrayOf(
                            0f,
                            0f,
                            0f,
                            0f,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius,
                            segmentCornerRadius
                        )
                        segmentUnSelectedDrawable.setColor(segmentUnSelectedStrokeColor)
                    }

                }
                else -> {
                    if (orientation == RadioGroup.HORIZONTAL) {
                        if (layoutParams is MarginLayoutParams) {
                            layoutParams.topMargin = segmentStrokeWidth
                            layoutParams.bottomMargin = segmentStrokeWidth
                        }
                    } else if (orientation == RadioGroup.VERTICAL) {
                        if (layoutParams is MarginLayoutParams) {
                            layoutParams.leftMargin = segmentStrokeWidth
                            layoutParams.rightMargin = segmentStrokeWidth
                        }
                    }
                    segmentSelectedDrawable.setColor(RED)
                    segmentUnSelectedDrawable.setColor(segmentUnSelectedStrokeColor)

                }
            }
            stateListDrawable.addState(intArrayOf(android.R.attr.state_checked), segmentSelectedDrawable)
            stateListDrawable.addState(intArrayOf(), segmentUnSelectedDrawable)
            child.setBackgroundDrawable(stateListDrawable)
        }
    }

}
