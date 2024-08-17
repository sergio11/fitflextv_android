package com.dreamsoftware.fitflextv.di

import android.content.Context
import com.dreamsoftware.fitflextv.ui.screens.favorites.FavoritesScreenSimpleErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.profiles.save.SaveProfileScreenSimpleErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.signin.SignInScreenSimpleErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.signup.SignUpScreenSimpleErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingScreenSimpleErrorMapper
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UiModule {

    @Provides
    @ViewModelScoped
    @SignUpScreenErrorMapper
    fun provideSignUpScreenSimpleErrorMapper(
        @ApplicationContext context: Context
    ): IFudgeTvErrorMapper =
        SignUpScreenSimpleErrorMapper(context = context)

    @Provides
    @ViewModelScoped
    @SignInScreenErrorMapper
    fun provideSignInScreenSimpleErrorMapper(
        @ApplicationContext context: Context
    ): IFudgeTvErrorMapper =
        SignInScreenSimpleErrorMapper(context = context)

    @Provides
    @ViewModelScoped
    @SaveProfileScreenErrorMapper
    fun provideSaveProfileScreenErrorMapper(
        @ApplicationContext context: Context
    ): IFudgeTvErrorMapper =
        SaveProfileScreenSimpleErrorMapper(context = context)

    @Provides
    @ViewModelScoped
    @FavoritesScreenErrorMapper
    fun provideFavoritesScreenSimpleErrorMapper(
        @ApplicationContext context: Context
    ): IFudgeTvErrorMapper =
        FavoritesScreenSimpleErrorMapper(context = context)

    @Provides
    @ViewModelScoped
    @TrainingScreenErrorMapper
    fun provideTrainingScreenSimpleErrorMapper(
        @ApplicationContext context: Context
    ): IFudgeTvErrorMapper =
        TrainingScreenSimpleErrorMapper(context = context)
}
