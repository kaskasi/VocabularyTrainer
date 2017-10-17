package de.fluchtwege.vocabulary

import android.app.Application
import android.arch.persistence.room.Room
import de.fluchtwege.vocabulary.di.AppComponent
import de.fluchtwege.vocabulary.di.AppModule
import de.fluchtwege.vocabulary.di.DaggerAppComponent
import de.fluchtwege.vocabulary.persistance.UntitledDatabase

class Vocabulary : Application() {

    lateinit var application: Vocabulary
    lateinit var database: UntitledDatabase

    companion object {
        lateinit var appComponent: AppComponent
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
                .appModule(AppModule(application))
                .build()
    }
}
