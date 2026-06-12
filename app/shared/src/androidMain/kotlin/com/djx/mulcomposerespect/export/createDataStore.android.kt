package com.djx.mulcomposerespect.export

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory

actual class DataStoreFactory(
    private val context: Context
) {
    actual fun create(): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.filesDir.resolve(dataStoreFileName)
            }
        )
    }
}