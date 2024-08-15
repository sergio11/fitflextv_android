package com.dreamsoftware.fitflextv.ui.screens.subscription

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO

interface ISubscriptionScreenActionListener {
    fun onSubscriptionOptionUpdated(subscription: SubscriptionBO)
    fun onSubscribe()
    fun onRestorePurchases()
    fun onCompleted()
    fun onErrorAccepted()
}