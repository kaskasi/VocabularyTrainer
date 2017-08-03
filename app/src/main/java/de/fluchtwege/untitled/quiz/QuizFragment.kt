package de.fluchtwege.untitled.quiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.databinding.FragmentQuizBinding
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.questions.QuestionsFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class QuizFragment: Fragment() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    lateinit var viewModel: QuizViewModel

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Untitled.appComponent.inject(this)
        val lessonName = activity.intent.getStringExtra(QuestionsFragment.KEY_LESSON_NAME)
        viewModel = QuizViewModel(lessonName, lessonsRepository)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentQuizBinding.inflate(inflater!!)
        binding.viewModel = viewModel
        binding.next.setOnClickListener { viewModel.onNext { activity.finish() } }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        disposable = viewModel.getLesson()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}