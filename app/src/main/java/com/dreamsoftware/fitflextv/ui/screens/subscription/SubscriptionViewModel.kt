package com.dreamsoftware.fitflextv.ui.screens.subscription


import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.model.UserSubscriptionBO
import com.dreamsoftware.fitflextv.domain.usecase.AddUserSubscriptionUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSubscriptionsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserSubscriptionUseCase
import com.dreamsoftware.fitflextv.domain.usecase.RemoveUserSubscriptionUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase,
    private val addUserSubscriptionUseCase: AddUserSubscriptionUseCase,
    private val removeUserSubscriptionUseCase: RemoveUserSubscriptionUseCase,
    private val getUserSubscriptionUseCase: GetUserSubscriptionUseCase
) : FudgeTvViewModel<SubscriptionUiState, SubscriptionSideEffects>(), ISubscriptionScreenActionListener {

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
        uiState.value.selectedSubscription?.let {
            executeUseCaseWithParams(
                useCase = addUserSubscriptionUseCase,
                params = AddUserSubscriptionUseCase.Params(
                    subscriptionId = it.id,
                    months = it.periodTime.toInt()
                ),
                onSuccess = ::onAddSubscriptionCompleted
            )
        }
    }

    override fun onRestorePurchases() {
        executeUseCase(
            useCase = removeUserSubscriptionUseCase,
            onSuccess = ::onSubscriptionRemoved
        )
    }

    override fun onNotInterested() {
        launchSideEffect(SubscriptionSideEffects.NotInterested)
    }

    override fun onCompleted() {
        updateState {
            it.copy(
                showSubscriptionAddedDialog = false,
                hasActiveSubscription = true
            )
        }
        launchSideEffect(SubscriptionSideEffects.AddSubscriptionCompleted)
    }

    private fun onAddSubscriptionCompleted(isAdded: Boolean) {
        if(isAdded) {
            updateState {
                it.copy(
                    showSubscriptionAddedDialog = true,
                    hasActiveSubscription = true
                )
            }
        }
    }

    private fun onSubscriptionRemoved(isRemoved: Boolean) {
        if(isRemoved) {
            updateState {
                it.copy(
                    hasActiveSubscription = false,
                    selectedSubscription = null
                )
            }
        }
    }

    private fun onGetSubscriptionsCompleted(subscriptionList: List<SubscriptionBO>) {
        updateState { it.copy(subscriptionList = subscriptionList) }
        executeUseCase(
            useCase = getUserSubscriptionUseCase,
            onSuccess = ::onGetUserSubscriptionCompleted
        )
    }

    private fun onGetUserSubscriptionCompleted(userSubscriptionBO: UserSubscriptionBO) {
        updateState {
            it.copy(
                selectedSubscription = it.subscriptionList.find { sub -> sub.id == userSubscriptionBO.subscriptionId },
                hasActiveSubscription = true
            )
        }
    }
}

data class SubscriptionUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val showSubscriptionAddedDialog: Boolean = false,
    val hasActiveSubscription: Boolean = false,
    val subscriptionList: List<SubscriptionBO> = emptyList(),
    val selectedSubscription: SubscriptionBO? = null
): UiState<SubscriptionUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SubscriptionUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SubscriptionSideEffects: SideEffect {
    data object AddSubscriptionCompleted: SubscriptionSideEffects
    data object NotInterested: SubscriptionSideEffects
}