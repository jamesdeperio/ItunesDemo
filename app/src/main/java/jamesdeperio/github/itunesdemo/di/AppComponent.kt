package jamesdeperio.github.itunesdemo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import jamesdeperio.github.itunesdemo.di.module.ActivityBindingModule
import jamesdeperio.github.itunesdemo.di.module.ApplicationBindingModule
import jamesdeperio.github.itunesdemo.di.module.FragmentBindingModule
import jamesdeperio.github.itunesdemo.di.scope.ApplicationScope

/*
dependency injection builder
 */
@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationBindingModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    override fun inject(instance: DaggerApplication?)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}