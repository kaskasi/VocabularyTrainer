package de.fluchtwege.untitled.vocabulary

import android.os.Bundle
import android.support.v4.app.Fragment
import de.fluchtwege.untitled.Untitled
import javax.inject.Inject

class LessonsFragment: Fragment() {

    @Inject
    lateinit var lessonRepository: LessonRepository

    lateinit var viewModel: LessonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Untitled.appComponent.inject(this)
        viewModel = LessonsViewModel(lessonRepository)
    }
}