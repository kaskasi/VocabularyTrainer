package de.fluchtwege.vocabulary.questions

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import de.fluchtwege.vocabulary.R
import de.fluchtwege.vocabulary.Vocabulary
import de.fluchtwege.vocabulary.addquestion.AddQuestionActivity
import de.fluchtwege.vocabulary.databinding.FragmentQuestionsBinding
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import de.fluchtwege.vocabulary.quiz.QuizActivity
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class QuestionsFragment : Fragment() {

    companion object {
        const val KEY_LESSON_NAME = "lesson_name"
        const val KEY_QUESTION_POSITION = "question_position"
    }

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    lateinit var viewModel: QuestionsViewModel
    lateinit var questionsAdapter: QuestionsAdaper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Vocabulary.appComponent.inject(this)
        val lessonName = activity.intent.getStringExtra(KEY_LESSON_NAME)
        viewModel = QuestionsViewModel(lessonName, lessonsRepository)
        questionsAdapter = QuestionsAdaper(viewModel, this::editQuestion, this::deleteQuestion )
    }

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentQuestionsBinding.inflate(inflater!!)
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

    private fun editQuestion(position: Int) {
        val openAddQuestion = Intent(context, AddQuestionActivity::class.java)
        openAddQuestion.putExtra(KEY_LESSON_NAME, viewModel.lessonName)
        openAddQuestion.putExtra(KEY_QUESTION_POSITION, position)
        startActivity(openAddQuestion)
    }

    private fun deleteQuestion(position: Int) {
        disposable = viewModel.deleteQuestion(position, questionsAdapter::notifyDataSetChanged)
    }

    private fun addQuestion() {
        val openAddQuestion = Intent(context, AddQuestionActivity::class.java)
        openAddQuestion.putExtra(KEY_LESSON_NAME, viewModel.lessonName)
        startActivity(openAddQuestion)
    }

    private fun startQuiz() {
        val openQuiz = Intent(context, QuizActivity::class.java)
        openQuiz.putExtra(KEY_LESSON_NAME, viewModel.lessonName)
        startActivity(openQuiz)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}