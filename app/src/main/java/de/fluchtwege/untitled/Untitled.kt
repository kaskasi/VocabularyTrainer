package de.fluchtwege.untitled

import android.app.Application
import de.fluchtwege.untitled.di.AppComponent
import de.fluchtwege.untitled.di.AppModule
import de.fluchtwege.untitled.di.DaggerAppComponent

class Untitled : Application() {

    lateinit var application: Untitled

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule())
                .build()
    }
}
