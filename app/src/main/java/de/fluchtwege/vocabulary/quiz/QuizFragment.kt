package de.fluchtwege.vocabulary.quiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.vocabulary.Vocabulary
import de.fluchtwege.vocabulary.analytics.Events
import de.fluchtwege.vocabulary.configuration.Configuration
import de.fluchtwege.vocabulary.databinding.FragmentQuizBinding
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import de.fluchtwege.vocabulary.questions.QuestionsFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class QuizFragment: Fragment() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    @Inject
    lateinit var events: Events

    @Inject
    lateinit var configuration: Configuration

    lateinit var viewModel: QuizViewModel

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Vocabulary.appComponent.inject(this)
        val lessonName = activity.intent.getStringExtra(QuestionsFragment.KEY_LESSON_NAME)
        viewModel = QuizViewModel(lessonName, lessonsRepository, events, configuration)
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