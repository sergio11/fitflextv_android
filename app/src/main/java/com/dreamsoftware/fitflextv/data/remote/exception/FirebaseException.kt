package com.dreamsoftware.fitflextv.data.remote.exception

open class FirebaseException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Auth Data Source
class AuthException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)
class SignInException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)
class SignUpException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)

// Routines
class FetchRemoteRoutinesException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)
class FetchRemoteRoutineByIdException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)

// Series
class FetchRemoteSeriesException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)
class FetchRemoteSeriesByIdException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)

// Categories
class FetchRemoteCategoriesException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)

// Sessions
class FetchRemoteSessionsException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)

// Workout
class FetchRemoteWorkoutsException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)
class FetchRemoteWorkoutByIdException(message: String? = null, cause: Throwable? = null): FirebaseException(message, cause)