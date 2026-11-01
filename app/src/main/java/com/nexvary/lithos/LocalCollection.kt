package com.nexvary.lithos

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/** Device-only prototype store. Migration to Room will preserve this format. */
class LocalCollection(context: Context) {
    private val preferences = context.applicationContext.getSharedPreferences("lithos_collection_v1", Context.MODE_PRIVATE)

    data class Specimen(val id: Long, val name: String, val note: String)

    fun all(): List<Specimen> = try {
        val json = JSONArray(preferences.getString("specimens", "[]"))
        (0 until json.length()).map { index ->
            val item = json.getJSONObject(index)
            Specimen(item.getLong("id"), item.getString("name"), item.optString("note"))
        }
    } catch (_: Exception) { emptyList() }

    fun add(name: String, note: String): Boolean {
        val safeName = name.trim().take(120)
        if (safeName.isEmpty() || all().size >= 500) return false
        val items = all() + Specimen(System.currentTimeMillis(), safeName, note.trim().take(1000))
        return save(items)
    }

    fun remove(id: Long): Boolean = save(all().filterNot { it.id == id })

    private fun save(items: List<Specimen>): Boolean {
        val json = JSONArray()
        items.forEach {
            json.put(JSONObject().put("id", it.id).put("name", it.name).put("note", it.note))
        }
        return preferences.edit().putString("specimens", json.toString()).commit()
    }
}
