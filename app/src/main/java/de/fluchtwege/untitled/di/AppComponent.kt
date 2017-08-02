package de.fluchtwege.untitled.di

import dagger.Component
import de.fluchtwege.untitled.addlesson.AddLessonFragment
import de.fluchtwege.untitled.addquestion.AddQuestionFragment
import de.fluchtwege.untitled.lessons.LessonsFragment
import de.fluchtwege.untitled.questions.QuestionsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(lessonsFragment: LessonsFragment)
    fun inject(questionsFragment: QuestionsFragment)
    fun inject(addQuestionFragment: AddQuestionFragment)
    fun inject(addLessonFragment: AddLessonFragment)
}