package de.fluchtwege.vocabulary.lessons

import de.fluchtwege.vocabulary.models.Lesson
import de.fluchtwege.vocabulary.models.Question
import de.fluchtwege.vocabulary.persistance.UntitledDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single

class RoomLessonsRepository(val lessonsController: LessonsController, val database: UntitledDatabase,
                            val observeOn: Scheduler, val subscribeOn: Scheduler) : LessonsRepository {

    override fun addLesson(lesson: Lesson) = Completable.defer {
        database.lessonDao().insert(lesson)
        return@defer Completable.complete()
    }.subscribeOn(subscribeOn)
            .observeOn(observeOn)

    override fun storeCurrentLessons() = database.lessonDao().getLessons()
            .subscribeOn(subscribeOn)
            .flatMapCompletable { storeLessonsRemotely(it) }
            .andThen(getLessons())
            .observeOn(observeOn)

    private fun storeLessonsRemotely(lessons: List<Lesson>) =
            lessonsController.storeLessons(lessons)

    override fun deleteLesson(name: String): Completable = Completable.defer {
        database.lessonDao().deleteLesson(name)
        return@defer Completable.complete()
    }.subscribeOn(subscribeOn).observeOn(observeOn)

    override fun updateLesson(lesson: Lesson) = Completable.defer{
        database.lessonDao().update(lesson)
        return@defer Completable.complete()
    }.subscribeOn(subscribeOn).observeOn(observeOn)

    override fun getLesson(name: String) = database.lessonDao().getLesson(name)
            .subscribeOn(subscribeOn).observeOn(observeOn)

    override fun getLessonsFromRemote() = lessonsController.getLessons()
            .subscribeOn(subscribeOn)
            .flatMapCompletable { saveRemoteLessons(it) }
            .andThen(getLessons())
            .observeOn(observeOn)

    private fun saveRemoteLessons(lessons: List<Lesson>) = Completable.defer {
        database.lessonDao().clearLessons()
        lessons.forEach { database.lessonDao().insert(it) }
        return@defer Completable.complete()
    }

    override fun getLessons(): Flowable<List<Lesson>> = database.lessonDao().getLessons()
            .subscribeOn(subscribeOn).observeOn(observeOn)

    override fun addQuestion(lessonName: String, question: Question) = database.lessonDao()
            .getLesson(lessonName)
            .subscribeOn(subscribeOn)
            .first(Lesson("", "", emptyList()))
            .flatMapCompletable {
                val questions = it.questions.toMutableList()
                questions.add(question)
                it.questions = questions
                database.lessonDao().update(it)
                return@flatMapCompletable Completable.complete()

            }.observeOn(observeOn)

    override fun deleteQuestion(lessonName: String, position: Int) = database.lessonDao()
            .getLesson(lessonName)
            .subscribeOn(subscribeOn)
            .first(Lesson("", "", emptyList()))
            .flatMapCompletable {
                val questions = it.questions.toMutableList()
                questions.removeAt(position)
                it.questions = questions
                database.lessonDao().update(it)
                return@flatMapCompletable Completable.complete()
            }.observeOn(observeOn)

    override fun getQuestion(lessonName: String, questionPosition: Int) = database.lessonDao()
            .getLesson(lessonName)
            .subscribeOn(subscribeOn)
            .first(Lesson("", "", emptyList()))
            .flatMap { return@flatMap Single.just(it.questions[questionPosition]) }
            .observeOn(observeOn)

    override fun updateQuestion(lessonName: String, position: Int, question: Question) = database.lessonDao()
            .getLesson(lessonName)
            .subscribeOn(subscribeOn)
            .first(Lesson("", "", emptyList()))
            .flatMapCompletable {
                val questions = it.questions.toMutableList()
                questions[position] = question
                it.questions = questions
                database.lessonDao().update(it)
                return@flatMapCompletable Completable.complete()
            }.observeOn(observeOn)

    override fun clearRepository() = Completable.defer {
        database.lessonDao().clearLessons()
        return@defer Completable.complete()
    }.subscribeOn(subscribeOn)
            .andThen(getLessons())
            .observeOn(observeOn)

}