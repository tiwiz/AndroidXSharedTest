package offline.open.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.overview_fragment.*
import offline.open.R
import offline.open.common.ARTICLE_ID
import offline.open.common.DetailHandler
import offline.open.detail.DetailFragment
import offline.open.models.LceView
import offline.open.models.Overview
import offline.open.models.OverviewDispatcher
import offline.open.repository.ArticleListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OverviewFragment : Fragment(), LceView<Overview>, DetailHandler {

    private val dispatcher: OverviewDispatcher by inject { parametersOf(this) }
    private val viewModel: ArticleListViewModel by viewModel()
    private val articleAdapter: ArticleAdapter by inject { parametersOf(viewLifecycleOwner, this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.overview_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.overview.observe(this, dispatcher)

        with(article_list) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articleAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetch()
    }

    override fun onLoading() {

    }

    override fun onSuccess(data: Overview) {
        articleAdapter.updateItems(data)
    }

    override fun onError(throwable: Throwable) {

    }

    override fun onArticleSelected(articleId: String) {
        val arguments = bundleOf(ARTICLE_ID to articleId)
        findNavController().navigate(R.id.detailFragment, arguments)
    }
}