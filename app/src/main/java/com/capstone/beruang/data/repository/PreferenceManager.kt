package com.capstone.beruang.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class PreferenceManager private constructor(context: Context) {

    companion object {
        private const val PREFS_NAME = "MyAppPrefs"
        const val KEY_IS_USER_LOGGED_IN = "isUserLoggedIn"

        @Volatile
        private var instance: PreferenceManager? = null

        fun getInstance(context: Context): PreferenceManager =
            instance ?: synchronized(this) {
                instance ?: PreferenceManager(context).also { instance = it }
            }
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
    fun setUserLoggedIn(isLoggedIn: Boolean) {
        putBoolean(KEY_IS_USER_LOGGED_IN, isLoggedIn)
    }
    // Add more methods as needed

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}
