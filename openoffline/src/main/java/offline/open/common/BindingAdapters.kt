package offline.open.common

import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

object BindingAdapters {

    private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        .withLocale(Locale.ITALY)
        .withZone(ZoneId.systemDefault())

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Picasso.get().load(url).into(view)
        }
    }

    @BindingAdapter("hideIfEmpty")
    @JvmStatic
    fun hideIfEmpty(view: ImageView, url: String?) {
        url?.let {
            if (url.isEmpty() || url.isBlank()) {
                view.visibility = GONE
            } else {
                view.visibility = VISIBLE
            }
        }
    }

    @BindingAdapter("timestamp")
    @JvmStatic
    fun timestamp(textView: TextView, timestamp: Instant?) {
        timestamp?.let {
            //TODO Move to tests
            textView.text = formatter.format(timestamp)
        }
    }

    @BindingAdapter("html")
    @JvmStatic
    fun html(webView: WebView, html: String?) {
        html?.let {

            webView.loadData(html, "text/html; charset=UTF-8", null)
        }
    }
}