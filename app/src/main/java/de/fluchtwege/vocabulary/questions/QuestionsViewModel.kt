package de.fluchtwege.vocabulary.questions

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import de.fluchtwege.vocabulary.BR
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import de.fluchtwege.vocabulary.models.Question

class QuestionsViewModel(val lessonName: String, val lessonsRepository: LessonsRepository) : BaseObservable() {

    var isLoading = false
    var questions: List<Question> = emptyList()

    fun loadQuestions(onQuestionsLoaded: () -> Unit) = lessonsRepository
            .getLesson(lessonName)
            .doOnSubscribe { setProgressVisible(true) }
            .subscribe(
                    {
                        setProgressVisible(false)
                        questions = it.questions
                        onQuestionsLoaded()
                    },
                    { onError(it) })

    private fun setProgressVisible(showProgress: Boolean) {
        isLoading = showProgress
        notifyPropertyChanged(BR.progressVisible)
    }

    private fun onError(error: Throwable) {
        setProgressVisible(false)
//        Log.e("ERROR", "we had a problem", error)
    }

    fun getQuestionViewModel(position: Int) = QuestionViewModel(questions[position])

    fun getNumberOfQuestions() = questions.size

    @Bindable
    fun isProgressVisible() = isLoading

    fun deleteQuestion(position: Int, onDeleted: () -> Unit) =
            lessonsRepository.deleteQuestion(lessonName, position).
                    subscribe({ onDeleted() }, { onError(it) })
}