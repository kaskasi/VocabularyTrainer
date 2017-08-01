package de.fluchtwege.untitled

import android.app.Application
import android.arch.persistence.room.Room
import de.fluchtwege.untitled.di.AppComponent
import de.fluchtwege.untitled.di.AppModule
import de.fluchtwege.untitled.di.DaggerAppComponent
import de.fluchtwege.untitled.persistance.UntitledDatabase

class Untitled : Application() {

    lateinit var application: Untitled

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var database: UntitledDatabase
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        initRoom()
        initDagger()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, UntitledDatabase::class.java, "untitled_database").build()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule())
                .build()
    }
}
