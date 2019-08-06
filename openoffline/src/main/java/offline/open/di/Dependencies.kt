package offline.open.di

import android.app.Application
import offline.open.BuildConfig
import offline.open.network.buildAPI
import offline.open.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
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

fun fetchModules() = listOf(openModule)

val openModule = module {

    single { buildAPI(BuildConfig.API_ENDPOINT) }

    single { FeedParser() }

    single { ArticleDatabase.build(get()).articleDao() }

    single<Repository> { OpenRepository(get(), get(), get()) }
}