package com.dreamsoftware.fitflextv.ui.screens.instructordetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonBackRowSchema
import com.dreamsoftware.fitflextv.ui.core.components.CommonCardDetails
import com.dreamsoftware.fitflextv.ui.core.components.CommonLoadingState
import com.dreamsoftware.fitflextv.ui.core.components.CommonNoContentState
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreenContent
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum

@Composable
internal fun InstructorDetailScreenContent(
    uiState: InstructorDetailUiState,
    actionListener: InstructorDetailActionListener
) {
    with(uiState) {
        CommonScreenContent(onErrorAccepted = actionListener::onErrorAccepted) {
            if (isLoading) {
                CommonLoadingState(modifier = Modifier.fillMaxSize())
            } else if (instructorDetail == null) {
                CommonNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.instructor_detail_not_available
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    CommonText(
                        type = CommonTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.instructor_detail_title,
                        textBold = true
                    )
                    CommonCardDetails(
                        modifier = Modifier.width(400.dp),
                        title = instructorDetail.name,
                        description = instructorDetail.description,
                        imageUrl = instructorDetail.imageUrl
                    )
                    CommonBackRowSchema(
                        onClickBack = actionListener::onBackPressed
                    )
                }
            }
        }
    }
}