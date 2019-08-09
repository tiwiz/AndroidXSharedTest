package offline.open.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import offline.open.R
import offline.open.common.ARTICLE_ID
import offline.open.databinding.DetailFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val articleId by lazy { arguments!!.getString(ARTICLE_ID) as String }
    private val detailViewModel: DetailViewModel by viewModel()
    private val shareActionProvider: ShareProvider by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<DetailFragmentBinding>(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = detailViewModel
            shareProvider = shareActionProvider
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.loadArticle(articleId)

        shareActionProvider.sharedLink.observe(viewLifecycleOwner, Observer<String> {
            shareUrl(it)
        })
    }

    private fun shareUrl(url: String) {
        val intent = ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setText(url)
                .createChooserIntent()

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}