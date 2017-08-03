package de.fluchtwege.untitled.lessons

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import de.fluchtwege.untitled.BR
import de.fluchtwege.untitled.models.Lesson
import io.reactivex.Completable
import io.reactivex.Flowable

class LessonsViewModel(val lessonsRepository: LessonsRepository) : BaseObservable() {

    var isLoading = false
    var lessons: List<Lesson> = emptyList()

    fun loadLessons(onLessonsLoaded: () -> Unit) = subscribeWithProgress(lessonsRepository.getLessons(), onLessonsLoaded)

    fun loadVocabulary(onVocabularyLoaded: () -> Unit) = subscribeWithProgress(
            lessonsRepository.getLessonsFromRemote(), onVocabularyLoaded)

    fun storeVocabulary() = subscribeWithProgress(lessonsRepository.storeCurrentLessons(), {})

    fun clearRepository() = subscribeWithProgress(lessonsRepository.clearRepository(), {})

    private fun subscribeWithProgress(flowable: Flowable<List<Lesson>>, onSuccess: () -> Unit) =
            flowable.doOnSubscribe { setProgress(true) }
                    .doOnError { setProgress(false) }
                    .subscribe({
                        lessons = it
                        setProgress(false)
                        onSuccess()
                    }, { onError(it) })


    private fun onError(error: Throwable) {
        Log.e("ERROR", "we had a problem", error)
    }

    fun hasLessons() = lessons.size > 0

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