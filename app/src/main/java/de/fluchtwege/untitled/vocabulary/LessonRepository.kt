package de.fluchtwege.untitled.vocabulary

import de.fluchtwege.untitled.models.Lesson
import io.reactivex.Single

interface LessonRepository {

    fun getLessons() : Single<List<Lesson>>

    fun getLession(position: Int) : Single<Lesson>
}