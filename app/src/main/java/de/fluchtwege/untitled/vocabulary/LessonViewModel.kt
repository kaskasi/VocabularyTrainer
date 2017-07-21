package de.fluchtwege.untitled.vocabulary

import android.databinding.BaseObservable
import android.databinding.Bindable
import de.fluchtwege.untitled.models.Lesson

class LessonViewModel(val lesson: Lesson) : BaseObservable() {

    @Bindable
    fun getName() = lesson.name

    @Bindable
    fun getDescription() = lesson.description
}