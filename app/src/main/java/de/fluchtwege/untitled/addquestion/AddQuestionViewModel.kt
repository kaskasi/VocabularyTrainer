package de.fluchtwege.untitled.addquestion

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.models.Question
import io.reactivex.Completable
import io.reactivex.disposables.Disposable

class AddQuestionViewModel(val lessonsRepository: LessonsRepository, val lessonName: String, val questionPosition: Int) : BaseObservable() {

    @Bindable
    var information: String = ""

    @Bindable
    var answer: String = ""

    fun save(onSaved: () -> Unit): Disposable? = Completable.defer {
        if (isNewQuestion()) {
            return@defer create()
        } else {
            return@defer update()
        }
    }.subscribe({ onSaved() }, { onError(it) })

    private fun create() = lessonsRepository.addQuestion(lessonName, Question(information, answer))

    private fun update() = lessonsRepository.updateQuestion(lessonName, questionPosition, Question(information, answer))

    fun isNewQuestion() = questionPosition == -1

    fun loadQuestion() : Disposable? = lessonsRepository.getQuestion(lessonName, questionPosition).subscribe({
        information = it.information
        answer = it.answer
        notifyChange()
    }, { onError(it) })


    private fun onError(error: Throwable) {
        Log.e("ERROR", "we had a problem", error)
    }

}