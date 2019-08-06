package offline.open.overview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import offline.open.models.LceView
import offline.open.models.Overview
import offline.open.models.OverviewDispatcher
import offline.open.repository.ArticleListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OverviewFragment : Fragment(), LceView<Overview> {

    private val dispatcher: OverviewDispatcher by inject { parametersOf(this) }
    private val viewModel: ArticleListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.overview.observe(this, dispatcher)
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetch()
    }

    override fun onLoading() {

    }

    override fun onSuccess(data: Overview) {

    }

    override fun onError(throwable: Throwable) {

    }

}