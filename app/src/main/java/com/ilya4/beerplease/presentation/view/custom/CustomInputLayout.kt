package com.ilya4.beerplease.presentation.view.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ilya4.beerplease.R
import kotlinx.android.synthetic.main.custom_input_layout.view.*

class CustomInputLayout: ConstraintLayout {

    lateinit var thisActiveDrawable: Drawable
    lateinit var thisNonactiveDrawable: Drawable

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        View.inflate(context, R.layout.custom_input_layout, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomInputLayout, 0 , 0)

        setTitleHint(if (typedArray.hasValue(R.styleable.CustomInputLayout_cil_main_hint_title))
            typedArray.getString(R.styleable.CustomInputLayout_cil_main_hint_title) else "")
        setBottomHint(if (typedArray.hasValue(R.styleable.CustomInputLayout_cil_bottom_hint))
            typedArray.getString(R.styleable.CustomInputLayout_cil_bottom_hint) else "")
        setActiveDrawable(if (typedArray.hasValue(R.styleable.CustomInputLayout_cil_active_drawable))
            typedArray.getDrawable(R.styleable.CustomInputLayout_cil_active_drawable) else return)
        setNonactiveDrawable(if (typedArray.hasValue(R.styleable.CustomInputLayout_cil_nonactive_drawable))
            typedArray.getDrawable(R.styleable.CustomInputLayout_cil_nonactive_drawable) else return)
        setInputType(typedArray.getInt(R.styleable.CustomInputLayout_cil_input_type, 0))

        typedArray?.recycle()

        setDefaultImage()
        initFocusListener()
    }

    private fun setTitleHint(hint: String) {
        hint.let {
            topHintText.text = hint
            textInputEditText.hint = hint
        }
    }

    private fun setBottomHint(bottomHint: String) {
        bottomHint.let {
            bottomHintText.text = bottomHint
        }
    }

    private fun setInputType(value: Int) {
        when (value) {
            0 -> textInputEditText.inputType = InputType.TYPE_CLASS_TEXT
            1 -> textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER
            2 -> textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
    }

    private fun setActiveDrawable(image: Drawable) {
        thisActiveDrawable = image
     }

    private fun setNonactiveDrawable(image: Drawable) {
        thisNonactiveDrawable = image
    }

    private fun setDefaultImage() {
        thisNonactiveDrawable.let {
            imageInput.setImageDrawable(thisNonactiveDrawable)
        }
    }

    private fun initFocusListener() {
        topHintText.setOnClickListener { textInputEditText.requestFocus() }
        bottomHintText.setOnClickListener { textInputEditText.requestFocus() }
        this.setOnClickListener { textInputEditText.requestFocus() }

        textInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || checkIfTextNotEmpty()) {
                onFocusEditText()
            } else {
                onDefocusEditText()
            }
        }
    }

    private fun checkIfTextNotEmpty(): Boolean = !TextUtils.isEmpty(textInputEditText.editableText)

    private fun onFocusEditText() {
        bottomHintText.visibility = View.GONE
        topHintText.visibility = View.VISIBLE
        imageInput.setImageDrawable(thisActiveDrawable)
    }

    private fun onDefocusEditText() {
        bottomHintText.visibility = View.VISIBLE
        topHintText.visibility = View.GONE
        imageInput.setImageDrawable(thisNonactiveDrawable)
    }
}