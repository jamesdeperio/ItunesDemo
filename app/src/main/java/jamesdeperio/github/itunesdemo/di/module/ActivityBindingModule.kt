package jamesdeperio.github.itunesdemo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import jamesdeperio.github.itunesdemo.di.scope.ActivityScope

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBindingModule {


}