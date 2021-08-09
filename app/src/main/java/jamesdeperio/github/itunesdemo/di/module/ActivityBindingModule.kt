package jamesdeperio.github.itunesdemo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import jamesdeperio.github.itunesdemo.feature.MainActivity

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun mainInjector(): MainActivity

}