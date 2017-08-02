package de.fluchtwege.untitled.persistance

import android.content.Context
import android.content.SharedPreferences

class RepositoryUrl(val context: Context) {

    private val preferenceName = "repositoryUrl"
    private val keyUrl = "keyUrl"

    lateinit var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }


    fun getUrl() = preferences.getString(keyUrl, "")

    fun saveUrl(newUrl: String) {
        preferences.edit().putString(keyUrl, newUrl).apply()
    }
}