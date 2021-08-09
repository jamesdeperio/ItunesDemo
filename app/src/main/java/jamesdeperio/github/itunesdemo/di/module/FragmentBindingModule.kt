package jamesdeperio.github.itunesdemo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import jamesdeperio.github.itunesdemo.di.scope.FragmentScope
import jamesdeperio.github.itunesdemo.feature.home.itunes.ItunesFragment
import jamesdeperio.github.itunesdemo.feature.home.itunes.ItunesModule
import jamesdeperio.github.itunesdemo.feature.home.itunesdetail.ItunesDetailFragment

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules=[ ItunesModule::class])
    internal abstract fun itunesInjector(): ItunesFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun itunesDetailInjector(): ItunesDetailFragment
}