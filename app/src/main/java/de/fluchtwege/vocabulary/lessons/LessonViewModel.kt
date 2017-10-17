package de.fluchtwege.vocabulary.lessons

import android.databinding.BaseObservable
import android.databinding.Bindable
import de.fluchtwege.vocabulary.models.Lesson

class LessonViewModel(val lesson: Lesson) : BaseObservable() {

    @Bindable
    fun getName() = lesson.name

    @Bindable
    fun getDescription() = lesson.description
}