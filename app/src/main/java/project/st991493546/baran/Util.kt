package project.st991493546.baran

import android.content.res.Resources
import android.text.Spanned
import androidx.core.text.HtmlCompat
import project.st991493546.baran.database.CardioEntity

fun formatCardio(cardio: List<CardioEntity>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        cardio.forEach {
            append("${it.cardioName} \t ${it.distance} \t ${it.duration} \t ${it.date}<br>")
        }
    }
    return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
}