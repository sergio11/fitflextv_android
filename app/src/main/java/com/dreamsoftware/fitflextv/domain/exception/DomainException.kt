package com.dreamsoftware.fitflextv.domain.exception

open class DomainRepositoryException(message: String? = null, cause: Throwable? = null): Exception(message, cause)
class RepositoryOperationException(message: String? = null, cause: Throwable? = null) : DomainRepositoryException(message, cause)

// Routines
class FetchRoutinesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchRoutineByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Sessions
class FetchSessionsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Categories
class FetchCategoriesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
