package de.fluchtwege.untitled.questions

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import de.fluchtwege.untitled.R
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.addquestion.AddQuestionActivity
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
        setHasOptionsMenu(true)
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
        disposable = viewModel.loadQuestions(questionsAdapter::notifyDataSetChanged)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_questions, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.add_question -> addQuestion()
            R.id.quiz_questions -> startQuiz()
        }
        return true
    }

    private fun addQuestion() {
        val openAddQuestion = Intent(context, AddQuestionActivity::class.java)
        openAddQuestion.putExtra(KEY_LESSON_NAME, viewModel.lessonName)
        startActivity(openAddQuestion)
    }

    private fun startQuiz() {


    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}