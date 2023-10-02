package com.boshpokemon.models.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ImageSpan

class CenteredImageSpan(
        ctx: Context,
        res: Int
) : ImageSpan(ctx, res) {
    override fun draw(
            canvas: Canvas,
            txt: CharSequence,
            start: Int,
            end: Int,
            x: Float,
            top: Int,
            y: Int,
            bottom: Int,
            paint: Paint
    ) {
        val d = drawable ?: return
        val fm = paint.fontMetricsInt ?: return
        val transY = (y + fm.descent + y + fm.ascent) / 2 - (d.bounds.bottom + d.bounds.top) / 2

        canvas.save()
        canvas.translate(x, transY.toFloat())
        d.draw(canvas)
        canvas.restore()
    }
}
