package com.trademe.purav.trademecategories

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by purav on 30/01/2018.
 */

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().create(this)
}
