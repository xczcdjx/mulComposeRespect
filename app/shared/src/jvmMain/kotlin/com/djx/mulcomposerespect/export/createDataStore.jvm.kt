package com.djx.mulcomposerespect.export

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import okio.Path.Companion.toPath
import java.io.File

actual class DataStoreFactory {
    actual fun create(): DataStore<Preferences> {
        val file = File(
            System.getProperty("user.home"),
            ".mulcomposerespect/$dataStoreFileName"
        )

        file.parentFile?.mkdirs()

        return PreferenceDataStoreFactory.createWithPath(
            produceFile = {
                file.absolutePath.toPath()
            }
        )
    }
}