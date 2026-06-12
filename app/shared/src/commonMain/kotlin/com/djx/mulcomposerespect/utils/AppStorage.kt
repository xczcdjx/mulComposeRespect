package com.djx.mulcomposerespect.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

class AppStorage(
    private val dataStore: DataStore<Preferences>
) {
    fun getStringFlow(key: String) =
        dataStore.data.map { prefs ->
            prefs[stringPreferencesKey(key)]
        }

    suspend fun putString(key: String, value: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey(key)] = value
        }
    }

    suspend fun remove(key: String) {
        dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey(key))
        }
    }

    suspend fun clear() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}