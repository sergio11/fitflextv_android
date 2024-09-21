package com.dreamsoftware.fitflextv.ui.screens.subscription

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface ISubscriptionScreenActionListener: IFudgeTvScreenActionListener {
    fun onSubscriptionOptionUpdated(subscription: SubscriptionBO)
    fun onSubscribe()
    fun onRestorePurchases()
    fun onCompleted()
    fun onNotInterested()
}