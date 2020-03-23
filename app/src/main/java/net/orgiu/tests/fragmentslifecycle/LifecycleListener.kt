package net.orgiu.tests.fragmentslifecycle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber

class LifecycleListener : FragmentManager.FragmentLifecycleCallbacks(){

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        Timber.d("onFragmentViewCreated - Fragment $f")
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        Timber.d("onFragmentCreated - Fragment $f")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        Timber.d("onFragmentDestroyed - Fragment $f")
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        Timber.d("onFragmentDestroyed - Fragment $f")
    }
}