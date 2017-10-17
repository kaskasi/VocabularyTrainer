package de.fluchtwege.vocabulary.persistance

import android.content.Context
import android.content.SharedPreferences

class RepositoryId(val context: Context) {

    private val preferenceName = "repositoryId"
    private val keyId = "keyId"
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }


    fun getId() = preferences.getString(keyId, "")

    fun saveId(newId: String) {
        preferences.edit().putString(keyId, newId).apply()
    }
}