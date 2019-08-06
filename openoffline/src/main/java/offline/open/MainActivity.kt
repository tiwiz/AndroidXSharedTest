package offline.open

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import offline.open.models.LceView
import offline.open.models.Overview
import offline.open.models.OverviewDispatcher
import offline.open.repository.ArticleListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), LceView<Overview> {

    private val dispatcher: OverviewDispatcher by inject { parametersOf(this) }
    private val viewModel: ArticleListViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
