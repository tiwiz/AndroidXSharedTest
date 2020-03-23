package net.orgiu.tests.fragmentslifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import net.orgiu.tests.R

class SimpleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btnCreate).setOnClickListener { addChildFragment() }
    }

    private fun addChildFragment() {
        childFragmentManager.beginTransaction()
            .add(SimpleFragment(), "SimpleFragment")
            .commit()
    }
}