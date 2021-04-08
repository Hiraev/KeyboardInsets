package com.example.keyboardinsets

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
                statusBarColor = Color.RED
                navigationBarColor = Color.RED
            }
        }
        setContentView(getView())
        super.onCreate(savedInstanceState)
    }

    private fun getView(): View {
        val container = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
        val coordinatorLayout = CoordinatorLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setBackgroundColor(Color.CYAN)
        }
        val editTextView = EditText(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            textSize = 40f
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
        }
        val scrollView = ScrollView(this).apply {
            layoutParams = CoordinatorLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }

        scrollView.addView(editTextView)
        coordinatorLayout.addView(scrollView)
        container.addView(coordinatorLayout)

        logApplyBottomInsets(container)

        return container
    }

    private fun logApplyBottomInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            Logger
                .getLogger(javaClass::class.java.simpleName)
                .info("[+] bottom: ${insets.systemWindowInsetBottom}")
            Toast
                .makeText(
                    this,
                    "bottom: ${insets.systemWindowInsetBottom}",
                    Toast.LENGTH_SHORT
                )
                .show()
            view.updatePadding(
                top = insets.systemWindowInsetTop,
                bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }

}