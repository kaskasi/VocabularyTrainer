package de.fluchtwege.vocabulary.analytics


interface Events {

    fun quizAnsweredCorrectly(lessonName: String, question: String)
}