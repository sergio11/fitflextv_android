package com.dreamsoftware.fitflextv.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.dreamsoftware.fitflextv.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.fitflextv.data.preferences.datasource.IUserPreferencesDataSource
import com.dreamsoftware.fitflextv.data.preferences.datasource.impl.ProfileSessionDataSourceImpl
import com.dreamsoftware.fitflextv.data.preferences.datasource.impl.UserPreferencesDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module providing dependencies related to DataStore preferences.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {

    /**
     * Companion object containing constants related to DataStore preferences.
     */
    private companion object {
        const val PREFERENCE_FILE_NAME = "fitflex_preferences"
    }

    /**
     * Provides a Moshi instance for JSON serialization/deserialization.
     *
     * @return A [Moshi] instance.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    /**
     * Provides a DataStore instance for storing preferences.
     *
     * @param context The application context.
     * @return A [DataStore] instance for preferences.
     */
    @Provides
    @Singleton
    fun providePreferenceDatastore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(PREFERENCE_FILE_NAME)
            }
        )

    /**
     * Provides an implementation of [IProfileSessionDataSource] for managing profile session-related data.
     * @param dataStore The DataStore instance used for storing preferences.
     * @param moshi The Moshi instance used for JSON serialization/deserialization.
     * @return An instance of [IProfileSessionDataSource] for profile session data management.
     */
    @Provides
    @Singleton
    fun provideProfileSessionDataSource(dataStore: DataStore<Preferences>, moshi: Moshi): IProfileSessionDataSource =
        ProfileSessionDataSourceImpl(dataStore, moshi)


    /**
     * Provides an implementation of [IUserPreferencesDataSource] for managing user preferences related data.
     * @param dataStore The DataStore instance used for storing preferences.
     * @param moshi The Moshi instance used for JSON serialization/deserialization.
     * @return An instance of [IUserPreferencesDataSource] for user preferences data management.
     */
    @Provides
    @Singleton
    fun provideUserPreferencesDataSource(dataStore: DataStore<Preferences>, moshi: Moshi): IUserPreferencesDataSource =
        UserPreferencesDataSourceImpl(dataStore, moshi)
}