package offline.open

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import offline.open.models.LceView
import offline.open.models.Overview

class MainActivity : AppCompatActivity(), LceView<Overview> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLoading() {

    }

    override fun onSuccess(data: Overview) {

    }

    override fun onError(throwable: Throwable) {

    }
}
