package de.fluchtwege.untitled.lessons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.databinding.FragmentLessonsBinding
import de.fluchtwege.untitled.persistance.RepositoryUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class LessonsFragment : Fragment() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    @Inject
    lateinit var repositoryUrl: RepositoryUrl

    lateinit var viewModel: LessonsViewModel

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Untitled.appComponent.inject(this)
        repositoryUrl.saveUrl("ocuhd")
        viewModel = LessonsViewModel(lessonsRepository)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLessonsBinding.inflate(inflater!!)
        val lessonAdapter = LessonAdapter(viewModel, context)
        binding.lessons.adapter = lessonAdapter
        val layoutManager = LinearLayoutManager(context)
        binding.lessons.layoutManager = layoutManager
        binding.lessons.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        binding.viewModel = viewModel
        disposable = viewModel.loadLessons(lessonAdapter::notifyDataSetChanged)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}