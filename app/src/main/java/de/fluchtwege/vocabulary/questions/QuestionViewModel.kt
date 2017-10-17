package de.fluchtwege.vocabulary.questions

import android.databinding.BaseObservable
import android.databinding.Bindable
import de.fluchtwege.vocabulary.models.Question

class QuestionViewModel(val question: Question) : BaseObservable() {

    @Bindable
    fun getInformation() = question.information

    @Bindable
    fun getAnswer() = question.answer

}