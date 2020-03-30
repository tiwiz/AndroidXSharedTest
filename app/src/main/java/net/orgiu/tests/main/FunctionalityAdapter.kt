package net.orgiu.tests.main

import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.orgiu.tests.databinding.FunctionsLayoutBinding

interface OnFunctionalityChosenListener {
    fun onFunctionalityChosen(functionality: Functionality<*>)
}

class FunctionalityViewHolder(
    private val binding: FunctionsLayoutBinding,
    private val listener: OnFunctionalityChosenListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(functionality: Functionality<*>) {
        binding.btnFunctions.setText(functionality.buttonTitle)

        binding.btnFunctions.setOnClickListener {
            listener.onFunctionalityChosen(functionality)
        }
    }
}

class FunctionalityAdapter(private val listener: OnFunctionalityChosenListener) :
    RecyclerView.Adapter<FunctionalityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionalityViewHolder =
        FunctionalityViewHolder(
            FunctionsLayoutBinding.inflate(from(parent.context), parent, false),
            listener
        )

    override fun onBindViewHolder(holder: FunctionalityViewHolder, position: Int) {
        holder.bindTo(functions[position])
    }

    override fun getItemCount(): Int = functions.size
}