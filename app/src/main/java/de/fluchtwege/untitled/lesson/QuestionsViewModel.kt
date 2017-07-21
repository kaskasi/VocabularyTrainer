package de.fluchtwege.untitled.lesson

import android.databinding.BaseObservable
import de.fluchtwege.untitled.models.Question

class QuestionsViewModel(val questionRepository: QuestionRepository) : BaseObservable() {

    var isLoading = false
    var questions: List<Question> = emptyList()

    fun loadQuestions() = questionRepository
            .getQuestions(0)
            .doOnSubscribe { isLoading = true }
            .doAfterTerminate { isLoading = false }
            .subscribe(
                    { questions = it },
                    { onError(it) })


    private fun onError(error: Throwable) {}

    fun getQuestionViewModel(position: Int) = QuestionViewModel(questions[position])

    fun getNumberOfQuestions() = questions.size


}