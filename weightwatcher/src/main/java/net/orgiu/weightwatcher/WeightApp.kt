package net.orgiu.weightwatcher

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.thesaurus.branchview.BranchIndicator
import timber.log.Timber

class WeightApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)

        BranchIndicator.attachTo(this, "develop")
    }
}