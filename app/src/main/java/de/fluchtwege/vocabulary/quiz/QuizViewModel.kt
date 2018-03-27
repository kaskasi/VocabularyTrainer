package de.fluchtwege.vocabulary.quiz

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import de.fluchtwege.vocabulary.analytics.Events
import de.fluchtwege.vocabulary.R
import de.fluchtwege.vocabulary.configuration.Configuration
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import de.fluchtwege.vocabulary.models.Lesson

class QuizViewModel(private val lessonName: String,
                    private val lessonsRepository: LessonsRepository,
                    private val events: Events,
                    private val configuration: Configuration) : BaseObservable() {

    var currentQuestionIndex = 0

    var lesson: Lesson? = null

    @Bindable
    var enteredAnswer = ""
        set(value) {
            field = value
            logCorrectAnswer()
            notifyChange()
        }

    private fun logCorrectAnswer() {
        if (enteredAnswer == getCurrentQuestion()?.answer) {
            getCurrentQuestion()?.let { question ->
                lesson?.let { currentLesson ->
                    events.quizAnsweredCorrectly(currentLesson.name, question.information)
                }
            }
        }
    }

    fun getLesson() = lessonsRepository
            .getLesson(lessonName)
            .doOnNext { lesson = it }
            .subscribe({ notifyChange() }, { onError(it) })

    private fun onError(error: Throwable) {
        Log.e("ERROR", "we had a problem", error)
    }

    @Bindable
    fun getInformation() = getCurrentQuestion()?.information ?: ""

    @Bindable
    fun getAnswerColor() = when {
        enteredAnswer.isEmpty() -> R.color.colorPrimary
        enteredAnswer == getCurrentQuestion()?.answer -> R.color.green
        else -> R.color.red
    }

    @Bindable
    fun isNextButtonEnabled() = enteredAnswer.isNotEmpty()

    @Bindable
    fun getNextButtonText() = if (hasNextQuestion()) R.string.next else R.string.done

    @Bindable
    fun getNextButtonColor() = if (configuration.isNextButtonBlack()) R.color.black else R.color.colorPrimary

    private fun hasNextQuestion() = currentQuestionIndex < lesson?.questions?.size?.minus(1) ?: 0

    private fun getCurrentQuestion() = lesson?.questions?.get(currentQuestionIndex)

    fun onNext(onDone: () -> Unit) {
        if (!hasNextQuestion()) {
            onDone()
            return
        }
        enteredAnswer = ""
        currentQuestionIndex++
        notifyChange()
    }
}