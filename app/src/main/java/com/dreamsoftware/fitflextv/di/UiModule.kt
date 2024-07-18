package com.dreamsoftware.fitflextv.di

import android.content.Context
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.profiles.save.SaveProfileScreenSimpleErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.signin.SignInScreenSimpleErrorMapper
import com.dreamsoftware.fitflextv.ui.screens.signup.SignUpScreenSimpleErrorMapper
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
    ): IErrorMapper =
        SignUpScreenSimpleErrorMapper(context = context)

    @Provides
    @ViewModelScoped
    @SignInScreenErrorMapper
    fun provideSignInScreenSimpleErrorMapper(
        @ApplicationContext context: Context
    ): IErrorMapper =
        SignInScreenSimpleErrorMapper(context = context)


    @Provides
    @ViewModelScoped
    @SaveProfileScreenErrorMapper
    fun provideSaveProfileScreenErrorMapper(
        @ApplicationContext context: Context
    ): IErrorMapper =
        SaveProfileScreenSimpleErrorMapper(context = context)
}
