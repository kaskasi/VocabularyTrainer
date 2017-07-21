package de.fluchtwege.untitled.vocabulary

import android.databinding.BaseObservable
import de.fluchtwege.untitled.models.Lesson

class LessonsViewModel(val lessonRepository: LessonRepository) : BaseObservable() {

    var isLoading = false
    var lessons: List<Lesson> = emptyList()

    fun loadLessons() = lessonRepository
            .getLessons()
            .doOnSubscribe { isLoading = true }
            .subscribe(
                    { lessons = it },
                    { onError(it) })


    private fun onError(error: Throwable) {

    }

    fun getLessonViewModel(position: Int) = LessonViewModel(lessons[position])

    fun getNumberOfLessons() = lessons.size
}