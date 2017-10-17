package de.fluchtwege.vocabulary.di

import dagger.Component
import de.fluchtwege.vocabulary.addlesson.AddLessonFragment
import de.fluchtwege.vocabulary.addquestion.AddQuestionFragment
import de.fluchtwege.vocabulary.lessons.LessonsFragment
import de.fluchtwege.vocabulary.questions.QuestionsFragment
import de.fluchtwege.vocabulary.quiz.QuizFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(lessonsFragment: LessonsFragment)
    fun inject(questionsFragment: QuestionsFragment)
    fun inject(addQuestionFragment: AddQuestionFragment)
    fun inject(addLessonFragment: AddLessonFragment)
    fun inject(quizFragment: QuizFragment)
}