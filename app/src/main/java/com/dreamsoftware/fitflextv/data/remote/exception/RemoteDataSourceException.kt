package com.dreamsoftware.fitflextv.data.remote.exception

open class RemoteDataSourceException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Auth Data Source
class AuthExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class SignInExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class SignUpExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Routines
class FetchRemoteRoutinesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteRoutineByIdExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteRoutineByCategoryExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteFeaturedRoutinesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Series
class FetchRemoteSeriesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteSeriesByIdExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteSeriesByCategoryExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteFeaturedSeriesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Challenges
class FetchRemoteChallengesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteChallengesByIdExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteChallengesByCategoryExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteFeaturedChallengesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Categories
class FetchRemoteCategoriesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteCategoryByIdExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Workout
class FetchRemoteWorkoutsExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteWorkoutByIdExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteWorkoutByCategoryExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteFeaturedWorkoutsExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Profiles
class FetchRemoteProfilesExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class UpdateRemoteProfileExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class DeleteRemoteProfileExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class CreateRemoteProfileExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class VerifyRemoteProfileExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteProfileByIdExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Users
class CreateRemoteUserDetailExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class UpdateRemoteUserDetailExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRemoteUserDetailExceptionRemote(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class UpdateProfilesCountException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)