package net.orgiu.weightwatcher

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

@UnstableDefault
class WeightRepository(context: Context) {

    companion object {
        private const val STORAGE_KEY = "WeightData"
    }

    private val storage = PreferenceManager.getDefaultSharedPreferences(context)

    fun addWeight(weight: Weight) {
        val weights = getWeights().toMutableList()
        weights.add(weight)

        val json = Json.stringify(Weight.serializer().list, weights)
        storage.edit {
            putString(STORAGE_KEY, json)
        }
    }

    private fun getWeights(): List<Weight> =
        storage.getString(STORAGE_KEY, null)?.let {
            Json.parse(Weight.serializer().list, it)
        }.orEmpty()

    fun fetchUiModels(): List<UiModule> =
        getWeights().map { it.toUiModule() }

}