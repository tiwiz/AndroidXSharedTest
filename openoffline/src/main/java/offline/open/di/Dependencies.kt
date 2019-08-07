package offline.open.di

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import kotlinx.serialization.UnstableDefault
import offline.open.BuildConfig
import offline.open.common.DetailHandler
import offline.open.detail.DetailViewModel
import offline.open.models.*
import offline.open.network.buildAPI
import offline.open.overview.ArticleAdapter
import offline.open.overview.ArticleListViewModel
import offline.open.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
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

    single { FeedParser() }

    single { ArticleDatabase.build(get()).articleDao() }

    single { FeedUpdater(get(), get(), get()) }

    single<Repository> { OpenRepository(get(), get()) }
}

val overviewModule = module {

    single { (view: LceView<Overview>) -> LceDispatcher(view) }

    single { (lifecycleOwner: LifecycleOwner, handler: DetailHandler) ->
        ArticleAdapter(get(), lifecycleOwner, handler)
    }

    viewModel { ArticleListViewModel(get()) }
}

val detailModule = module {

    viewModel { DetailViewModel(get()) }
}