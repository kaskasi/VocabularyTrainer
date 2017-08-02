package de.fluchtwege.untitled.lessons

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import de.fluchtwege.untitled.BR
import de.fluchtwege.untitled.models.Lesson
import io.reactivex.Scheduler

class LessonsViewModel(val lessonsRepository: LessonsRepository) : BaseObservable() {

    var isLoading = false
    var lessons: List<Lesson> = emptyList()

    fun loadLessons(onLessonsLoaded: () -> Unit) = lessonsRepository
            .getLessons()
            .doOnSubscribe { setProgress(true) }
            .doOnError { setProgress(false) }
            .subscribe(
                    {
                        lessons = it
                        setProgress(false)
                        onLessonsLoaded()
                    },
                    {
                        onError(it)
                    })


    private fun onError(error: Throwable) {
        Log.e("ERROR", error.message)
    }

    fun getLessonViewModel(position: Int) = LessonViewModel(lessons[position])

    fun getNumberOfLessons() = lessons.size

    fun setProgress(showProgess: Boolean) {
        this.isLoading = showProgess
        notifyPropertyChanged(BR.progressVisible)
    }

    @Bindable
    fun isProgressVisible() = isLoading

    fun getLessonName(position: Int) = lessons[position].name
}