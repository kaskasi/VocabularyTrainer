package de.fluchtwege.vocabulary.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseEvents(private val context: Context) : Events {

    companion object {
        val EVENT_QUIZ_CORRECT = "quiz_correct"

        val PARAM_LESSON_NAME = "lesson_name"
        val PARAM_QUESTION = "question"
    }

    val firebaseInstance = FirebaseAnalytics.getInstance(context)

    override fun quizAnsweredCorrectly(lessonName: String, question: String) {
        firebaseInstance.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, createQuizCorrectBundle(lessonName, question))
    }

    private fun createQuizCorrectBundle(lessonName: String, question: String) = Bundle().apply {
        //putString(PARAM_LESSON_NAME, lessonName)
        //putString(PARAM_QUESTION, question)
        putString(FirebaseAnalytics.Param.ITEM_ID, "myId")
        putString(FirebaseAnalytics.Param.ITEM_NAME, "quiz_correct")

    }
}