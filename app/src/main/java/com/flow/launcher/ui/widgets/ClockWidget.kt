package com.flow.launcher.ui.widgets

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.flow.launcher.R
import java.text.SimpleDateFormat
import java.util.*

class ClockWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private lateinit var timeView: TextView
    private lateinit var dateView: TextView

    private val timeFmt = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val dateFmt = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())

    private val ticker = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context, intent: Intent) {
            update()
        }
    }

    init {
        inflate(context, R.layout.widget_clock, this)
        timeView = findViewById(R.id.clock_time)
        dateView = findViewById(R.id.clock_date)
        orientation = VERTICAL
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        context.registerReceiver(ticker, IntentFilter(Intent.ACTION_TIME_TICK))
        update()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        context.unregisterReceiver(ticker)
    }

    private fun update() {
        val now = Date()
        timeView.text = timeFmt.format(now)
        dateView.text = dateFmt.format(now).replaceFirstChar { it.uppercase() }
    }
}
