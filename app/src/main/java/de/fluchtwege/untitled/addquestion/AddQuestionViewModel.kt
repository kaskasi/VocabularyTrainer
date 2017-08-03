package de.fluchtwege.untitled.addquestion

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.models.Question

class AddQuestionViewModel(val lessonsRepository: LessonsRepository, val lessonName: String) : BaseObservable() {

    @Bindable
    var information: String = ""

    @Bindable
    var answer: String = ""

    fun save(onSaved: () -> Unit) = lessonsRepository.addQuestion(lessonName, Question(information, answer))
            .subscribe({ onSaved()}, { onError(it) })


    private fun onError(error: Throwable) {
        Log.e("ERROR", "we had a problem", error)
    }

}