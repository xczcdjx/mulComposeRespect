package com.djx.mulcomposerespect.export

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect class DataStoreFactory {
    fun create(): DataStore<Preferences>
}

internal const val dataStoreFileName = "app_storage.preferences_pb"