package com.dreamsoftware.fitflextv.data.preferences.exception

open class PreferencesDataSourceException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Profiles
class SaveProfileSelectedPreferenceException(message: String? = null, cause: Throwable? = null): PreferencesDataSourceException(message, cause)
class FetchProfileSelectedPreferenceException(message: String? = null, cause: Throwable? = null): PreferencesDataSourceException(message, cause)