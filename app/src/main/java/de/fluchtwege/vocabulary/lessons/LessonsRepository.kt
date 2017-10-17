package de.fluchtwege.vocabulary.lessons

import de.fluchtwege.vocabulary.models.Lesson
import de.fluchtwege.vocabulary.models.Question
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LessonsRepository {

    fun getLessons(): Flowable<List<Lesson>>

    fun getLessonsFromRemote(): Flowable<List<Lesson>>

    fun clearRepository(): Flowable<List<Lesson>>

    fun getLesson(name: String): Flowable<Lesson>

    fun addQuestion(lessonName: String, question: Question): Completable

    fun addLesson(lesson: Lesson): Completable

    fun storeCurrentLessons(): Flowable<List<Lesson>>

    fun deleteQuestion(lessonName: String, position: Int): Completable

    fun getQuestion(lessonName: String, questionPosition: Int): Single<Question>

    fun updateQuestion(lessonName: String, questionPosition: Int, question: Question): Completable

    fun deleteLesson(name: String): Completable

    fun updateLesson(lesson: Lesson): Completable
}