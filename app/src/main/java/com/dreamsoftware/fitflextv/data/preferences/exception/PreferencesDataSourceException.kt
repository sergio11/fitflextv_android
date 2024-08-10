package com.dreamsoftware.fitflextv.data.preferences.exception

open class PreferencesDataSourceException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Profiles
class SaveProfileSelectedPreferenceLocalException(message: String? = null, cause: Throwable? = null): PreferencesDataSourceException(message, cause)
class FetchProfileSelectedPreferenceLocalException(message: String? = null, cause: Throwable? = null): PreferencesDataSourceException(message, cause)

// User preferences
class SaveUserPreferencesLocalException(message: String? = null, cause: Throwable? = null): PreferencesDataSourceException(message, cause)
class FetchUserPreferencesLocalException(message: String? = null, cause: Throwable? = null): PreferencesDataSourceException(message, cause)