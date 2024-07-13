package com.dreamsoftware.fitflextv.domain.exception

open class DomainRepositoryException(message: String? = null, cause: Throwable? = null): Exception(message, cause)
class RepositoryOperationException(message: String? = null, cause: Throwable? = null) : DomainRepositoryException(message, cause)

// Training
class FetchTrainingsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchTrainingByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchTrainingByCategoryException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchTrainingsRecommendedException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Categories
class FetchCategoriesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchCategoryByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
