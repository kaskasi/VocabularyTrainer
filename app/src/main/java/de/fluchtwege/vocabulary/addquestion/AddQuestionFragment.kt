package de.fluchtwege.vocabulary.addquestion

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.vocabulary.Vocabulary
import de.fluchtwege.vocabulary.databinding.FragmentAddQuestionBinding
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import de.fluchtwege.vocabulary.questions.QuestionsFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AddQuestionFragment : Fragment() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    lateinit var viewModel: AddQuestionViewModel

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Vocabulary.appComponent.inject(this)
        val lessonName = activity.intent.getStringExtra(QuestionsFragment.KEY_LESSON_NAME)
        val questionPosition = activity.intent.getIntExtra(QuestionsFragment.KEY_QUESTION_POSITION, -1)
        viewModel = AddQuestionViewModel(lessonsRepository, lessonName, questionPosition)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAddQuestionBinding.inflate(inflater!!)

        binding.saveQuestion.setOnClickListener { _ -> saveQuestion() }
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.isNewQuestion()) {
            disposable = viewModel.loadQuestion()
        }
    }

    private fun saveQuestion() {
        disposable = viewModel.save { activity.finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}