package offline.open.common

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.text.Html
import android.view.View.GONE
import android.view.View.VISIBLE
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
    fun html(textView: TextView, html: String?) {
        html?.let {
            if (SDK_INT >= N) {
                textView.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                textView.text = Html.fromHtml(html)
            }
        }
    }
}