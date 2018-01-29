package com.trademe.purav.trademecategories

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by purav on 30/01/2018.
 */
@Module
abstract class ActivityBindingsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(ListingsActivity.Module::class))
    abstract fun listingsActivity(): ListingsActivity
}