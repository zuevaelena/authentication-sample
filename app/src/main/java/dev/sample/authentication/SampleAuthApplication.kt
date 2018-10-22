package dev.sample.authentication

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dev.sample.authentication.di.DaggerAppComponent

class SampleAuthApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
