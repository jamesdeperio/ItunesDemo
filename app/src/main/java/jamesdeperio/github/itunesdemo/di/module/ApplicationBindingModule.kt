package jamesdeperio.github.itunesdemo.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import jamesdeperio.github.itunesdemo.di.scope.ApplicationScope
import jamesdeperio.github.itunesdemo.network.NetworkManager
import jamesdeperio.github.itunesdemo.network.dao.AppDatabase
import jamesdeperio.github.itunesdemo.network.repository.RestRepository
import jamesdeperio.github.itunesdemo.network.service.RestService

/*
Application binding...
add app level dependencies here
 */
@Module
abstract class ApplicationBindingModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context

    companion object Provider {
        @ApplicationScope
        @Provides
        fun provideDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "db-itunes").build()

        @ApplicationScope
        @Provides
        fun provideNetworkManager(context: Context): NetworkManager = NetworkManager(context = context)

        @ApplicationScope
        @Provides
        fun provideRestRepository(networkManager: NetworkManager, database: AppDatabase): RestRepository =
            RestRepository(
                restService = networkManager.create(RestService::class.java) as RestService,
                database = database)
    }

}