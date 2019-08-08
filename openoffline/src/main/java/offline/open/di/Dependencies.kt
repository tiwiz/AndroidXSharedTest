package offline.open.di

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import kotlinx.serialization.UnstableDefault
import offline.open.BuildConfig
import offline.open.common.DetailHandler
import offline.open.detail.DetailViewModel
import offline.open.detail.ShareProvider
import offline.open.models.LceDispatcher
import offline.open.models.LceView
import offline.open.models.Overview
import offline.open.network.buildAPI
import offline.open.overview.ArticleAdapter
import offline.open.overview.ArticleListViewModel
import offline.open.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.injectDependencies() {
    val context = this

    startKoin {
        androidContext(context)
        androidLogger()
        modules(fetchModules())
    }
}

fun fetchModules() = listOf(openModule, overviewModule, detailModule)

@UnstableDefault
val openModule = module {

    single { buildAPI(BuildConfig.API_ENDPOINT) }

    single { StyleWrapper() }

    single { FeedParser() }

    single { ArticleDatabase.build(get()).articleDao() }

    single { FeedUpdater(get(), get(), get()) }

    single<Repository> { OpenRepository(get(), get(), get()) }
}

val overviewModule = module {

    factory { (view: LceView<Overview>) -> LceDispatcher(view) }

    factory { (lifecycleOwner: LifecycleOwner, handler: DetailHandler) ->
        ArticleAdapter(get(), lifecycleOwner, handler)
    }

    viewModel { ArticleListViewModel(get()) }
}

val detailModule = module {

    viewModel { DetailViewModel(get()) }

    factory { ShareProvider() }
}