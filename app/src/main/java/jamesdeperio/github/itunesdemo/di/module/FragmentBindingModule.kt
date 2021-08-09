package jamesdeperio.github.itunesdemo.di.module

import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBindingModule {

}