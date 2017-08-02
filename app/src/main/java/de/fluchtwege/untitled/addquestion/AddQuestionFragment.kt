package de.fluchtwege.untitled.addquestion

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.lessons.LessonsRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AddQuestionFragment : Fragment() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    lateinit var viewModel: AddQuestionViewModel

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Untitled.appComponent.inject(this)
        viewModel = AddQuestionViewModel(lessonsRepository)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val binding = FragmentAddQuestionBinding.inflate(inflater!!)
//        binding.viewModel = viewModel
//        return binding.root
        return null
    }

}