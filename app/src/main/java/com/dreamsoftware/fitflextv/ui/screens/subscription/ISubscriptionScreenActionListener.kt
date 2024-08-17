package com.dreamsoftware.fitflextv.ui.screens.subscription

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface ISubscriptionScreenActionListener: IFudgeScreenActionListener {
    fun onSubscriptionOptionUpdated(subscription: SubscriptionBO)
    fun onSubscribe()
    fun onRestorePurchases()
    fun onCompleted()
}