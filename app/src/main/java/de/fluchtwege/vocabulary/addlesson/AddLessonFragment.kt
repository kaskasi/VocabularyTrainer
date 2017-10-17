package de.fluchtwege.vocabulary.addlesson

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.vocabulary.Vocabulary
import de.fluchtwege.vocabulary.databinding.FragmentAddLessonBinding
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AddLessonFragment : Fragment() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    lateinit var viewModel: AddLessonViewModel

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Vocabulary.appComponent.inject(this)
        viewModel = AddLessonViewModel(lessonsRepository)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAddLessonBinding.inflate(inflater!!)
        binding.viewModel = viewModel
        binding.saveLesson.setOnClickListener { _ -> saveLesson() }
        return binding.root
    }

    private fun saveLesson() {
        disposable = viewModel.save { activity.finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}