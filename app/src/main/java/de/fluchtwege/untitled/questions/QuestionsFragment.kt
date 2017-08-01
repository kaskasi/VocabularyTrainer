package de.fluchtwege.untitled.questions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.databinding.FragmentQuestionsBinding
import de.fluchtwege.untitled.lessons.LessonsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class QuestionsFragment : Fragment() {

    companion object {
        const val KEY_LESSON_NAME = "lesson_name"
    }

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    lateinit var viewModel: QuestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Untitled.appComponent.inject(this)
        val lessonName = activity.intent.getStringExtra(KEY_LESSON_NAME)
        viewModel = QuestionsViewModel(lessonName, lessonsRepository)
    }

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentQuestionsBinding.inflate(inflater!!)
        val questionsAdapter = QuestionsAdaper(viewModel)
        val layoutManager = LinearLayoutManager(context)
        binding.questions.adapter = questionsAdapter
        binding.questions.layoutManager = layoutManager
        binding.questions.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        binding.viewModel = viewModel
        disposable = viewModel.loadQuestions(questionsAdapter::notifyDataSetChanged, AndroidSchedulers.mainThread())
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}