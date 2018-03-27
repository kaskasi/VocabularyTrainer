package de.fluchtwege.vocabulary.lessons

import android.databinding.BaseObservable
import android.databinding.Bindable
import de.fluchtwege.vocabulary.configuration.Configuration
import de.fluchtwege.vocabulary.models.Lesson

class LessonViewModel(private val lesson: Lesson,
                      private val configuration: Configuration) : BaseObservable() {

    @Bindable
    fun getName() = lesson.name

    @Bindable
    fun getDescription() = lesson.description

    @Bindable
    fun isShowingImage() = configuration.isShowingImage()

}