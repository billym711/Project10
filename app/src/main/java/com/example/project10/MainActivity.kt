package com.example.project10



import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: ViewGroup
    private lateinit var image: ImageView

    // default position of image
    private var xDelta = 0
    private var yDelta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.main)
        image = findViewById(R.id.image)

        // returns True if the listener has
        // consumed the event, otherwise False.
        image.setOnTouchListener(onTouchListener())
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchListener(): OnTouchListener {
        return OnTouchListener { view, event ->
            // position information
            // about the event by the user
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            // detecting user actions on moving
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }
                MotionEvent.ACTION_UP -> Toast.makeText(this,
                    "new location!", Toast.LENGTH_SHORT)
                    .show()
                MotionEvent.ACTION_MOVE -> {
                    // based on x and y coordinates (when moving image)
                    // and image is placed with it.
                    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }
            // reflect the changes on screen
            mainLayout.invalidate()
            true
        }
    }
}
