package de.fluchtwege.untitled.addquestion

import android.databinding.BaseObservable
import android.databinding.Bindable
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.models.Question

class AddQuestionViewModel(val lessonsRepository: LessonsRepository) : BaseObservable() {

    @Bindable
    var question : String = ""

    @Bindable
    var answer : String = ""

    fun save() : Unit {
        lessonsRepository.addQuestion(Question(question, answer))
    }

}