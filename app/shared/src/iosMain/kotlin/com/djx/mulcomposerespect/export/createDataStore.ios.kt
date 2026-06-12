package com.djx.mulcomposerespect.export

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DataStoreFactory {
    @OptIn(ExperimentalForeignApi::class)
    actual fun create(): DataStore<Preferences> {
        val documentDirectory = NSFileManager.defaultManager
            .URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )!!

        val path = documentDirectory.path + "/$dataStoreFileName"

        return PreferenceDataStoreFactory.createWithPath(
            produceFile = {
                path.toPath()
            }
        )
    }
}