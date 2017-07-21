package de.fluchtwege.untitled.di

import dagger.Component
import de.fluchtwege.untitled.vocabulary.LessonsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(lessonsFragment: LessonsFragment)

}