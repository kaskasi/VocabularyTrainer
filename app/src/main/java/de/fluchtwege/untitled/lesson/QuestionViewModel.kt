package de.fluchtwege.untitled.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable
import de.fluchtwege.untitled.models.Question

class QuestionViewModel(val question: Question) : BaseObservable() {

    @Bindable
    fun getInformation() = question.information

    @Bindable
    fun getAnswer() = question.answer

}