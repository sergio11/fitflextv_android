package com.dreamsoftware.fitflextv.ui.screens.subscription


import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val iInstructorRepository: IInstructorRepository
) : BaseViewModel<SubscriptionUiState, SubscriptionSideEffects>(), ISubscriptionScreenActionListener {

    override fun onGetDefaultState(): SubscriptionUiState = SubscriptionUiState()

    override fun onSubscriptionOptionUpdated(subscription: SubscriptionBO) {

    }

    override fun onSubscribe() {
    }

    override fun onRestorePurchases() {

    }
}

data class SubscriptionUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val subscriptionOptions: List<SubscriptionBO> = emptyList(),
    val selectedSubscription: SubscriptionBO? = null
): UiState<SubscriptionUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SubscriptionUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SubscriptionSideEffects: SideEffect