package project.st991493546.baran

import android.content.res.Resources
import android.text.Spanned
import androidx.core.text.HtmlCompat
import project.st991493546.baran.database.CardioEntity

fun formatCardio(cardio: List<CardioEntity>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        cardio.forEach {
            append("<b>ID:</b> ${it.id} <br> <b>Cardio name:</b>s ${it.cardioName} <br> <b>Distance:</b> ${it.distance} <br> <b>Duration:</b> ${it.duration} " +
                    "<br><b>Date:</b>  ${it.date} <br><br><br>")
        }
    }
    return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
}

