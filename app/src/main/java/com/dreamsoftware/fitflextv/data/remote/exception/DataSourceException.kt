package com.dreamsoftware.fitflextv.data.remote.exception

open class DataSourceException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Auth Data Source
class AuthException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class SignInException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class SignUpException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Routines
class FetchRemoteRoutinesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteRoutineByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteRoutineByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteFeaturedRoutinesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Series
class FetchRemoteSeriesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteSeriesByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteSeriesByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteFeaturedSeriesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Challenges
class FetchRemoteChallengesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteChallengesByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteChallengesByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteFeaturedChallengesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Categories
class FetchRemoteCategoriesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteCategoryByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Workout
class FetchRemoteWorkoutsException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteWorkoutByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteWorkoutByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteFeaturedWorkoutsException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Profiles
class FetchRemoteProfilesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class UpdateRemoteProfileException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class DeleteRemoteProfileException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class CreateRemoteProfileException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class VerifyRemoteProfileException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Users
class CreateRemoteUserDetailException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class UpdateRemoteUserDetailException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteUserDetailException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)