package jamesdeperio.github.itunesdemo.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import jamesdeperio.github.itunesdemo.di.scope.ApplicationScope
import jamesdeperio.github.itunesdemo.network.NetworkManager


@Module
abstract class ApplicationBindingModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context

    companion object Provider {
        @ApplicationScope
        @Provides
        fun provideNetworkManager(context: Context): NetworkManager = NetworkManager(context = context)
    }

}