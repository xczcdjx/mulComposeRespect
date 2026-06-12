package com.djx.mulcomposerespect.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.djx.mulcomposerespect.export.DataStoreFactory
import com.djx.mulcomposerespect.utils.AppStorage
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DataStoreModule {
    @Single
    fun provideDataStore(
        factory: DataStoreFactory
    ): DataStore<Preferences> {
        return factory.create()
    }

    @Single
    fun provideAppStorage(
        dataStore: DataStore<Preferences>
    ): AppStorage {
        return AppStorage(dataStore)
    }
}