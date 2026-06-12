package com.djx.mulcomposerespect.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppStorage(
    private val dataStore: DataStore<Preferences>
) {
    @PublishedApi
    internal val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun getStringFlow(key: String): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[stringPreferencesKey(key)]
        }
    }

    suspend fun putString(key: String, value: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey(key)] = value
        }
    }

    // Number: Int / Long / Float / Double / Short / Byte
    inline fun <reified T : Number> getNumberFlow(key: String): Flow<T?> {
        return getStringFlow(key).map { value ->
            if (value.isNullOrBlank()) {
                null
            } else {
                parseNumberOrNull<T>(value)
            }
        }
    }

    suspend fun putNumber(key: String, value: Number) {
        putString(key, value.toString())
    }

    @PublishedApi
    internal inline fun <reified T : Number> parseNumberOrNull(value: String): T? {
        return when (T::class) {
            Int::class -> value.toIntOrNull() as T?
            Long::class -> value.toLongOrNull() as T?
            Float::class -> value.toFloatOrNull() as T?
            Double::class -> value.toDoubleOrNull() as T?
            Short::class -> value.toShortOrNull() as T?
            Byte::class -> value.toByteOrNull() as T?
            else -> null
        }
    }

    // Boolean
    fun getBooleanFlow(key: String): Flow<Boolean?> {
        return getStringFlow(key).map { value ->
            value?.toBooleanStrictOrNull()
        }
    }

    suspend fun putBoolean(key: String, value: Boolean) {
        putString(key, value.toString())
    }

    // Char
    fun getCharFlow(key: String): Flow<Char?> {
        return getStringFlow(key).map { value ->
            value?.singleOrNull()
        }
    }

    suspend fun putChar(key: String, value: Char) {
        putString(key, value.toString())
    }

    // Object
    inline fun <reified T> getObjectFlow(key: String): Flow<T?> {
        return getStringFlow(key).map { value ->
            if (value.isNullOrBlank()) {
                null
            } else {
                runCatching {
                    json.decodeFromString<T>(value)
                }.getOrNull()
            }
        }
    }

    suspend inline fun <reified T> putObject(key: String, value: T) {
        val text = json.encodeToString(value)
        putString(key, text)
    }

    // List
    inline fun <reified T> getListFlow(key: String): Flow<List<T>> {
        return getObjectFlow<List<T>>(key).map { value ->
            value.orEmpty()
        }
    }

    suspend inline fun <reified T> putList(key: String, value: List<T>) {
        putObject(key, value)
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