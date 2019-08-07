package offline.open.detail

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import offline.open.common.ARTICLE_ID

class DetailFragment : Fragment() {

    private val articleId by lazy { arguments?.getString(ARTICLE_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(requireContext(), "Article: $articleId", Toast.LENGTH_SHORT).show()
    }
}