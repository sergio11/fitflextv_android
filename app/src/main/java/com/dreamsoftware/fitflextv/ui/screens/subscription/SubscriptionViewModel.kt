package com.dreamsoftware.fitflextv.ui.screens.subscription


import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.usecase.GetSubscriptionsUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : BaseViewModel<SubscriptionUiState, SubscriptionSideEffects>(), ISubscriptionScreenActionListener {

    fun loadData() {
        executeUseCase(
            useCase = getSubscriptionsUseCase,
            onSuccess = ::onGetSubscriptionsCompleted
        )
    }

    override fun onGetDefaultState(): SubscriptionUiState = SubscriptionUiState()

    override fun onSubscriptionOptionUpdated(subscription: SubscriptionBO) {
        updateState { it.copy(selectedSubscription = subscription) }
    }

    override fun onSubscribe() {
    }

    override fun onRestorePurchases() {

    }

    private fun onGetSubscriptionsCompleted(subscriptionList: List<SubscriptionBO>) {
        updateState { it.copy(subscriptionList = subscriptionList) }
    }
}

data class SubscriptionUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val subscriptionList: List<SubscriptionBO> = emptyList(),
    val selectedSubscription: SubscriptionBO? = null
): UiState<SubscriptionUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SubscriptionUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SubscriptionSideEffects: SideEffect