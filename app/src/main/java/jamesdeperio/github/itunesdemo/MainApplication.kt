package jamesdeperio.github.itunesdemo

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import jamesdeperio.github.itunesdemo.di.DaggerAppComponent

class MainApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }


}