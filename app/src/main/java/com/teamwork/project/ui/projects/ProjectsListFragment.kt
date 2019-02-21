package com.teamwork.project.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.teamwork.project.R
import com.teamwork.project.ui.BaseFragment
import com.teamwork.project.ui.adapters.ProjectsAdapter
import com.teamwork.project.ui.projectDetails.ProjectDetailsFragment
import com.teamwork.project.utils.DateUtils
import com.teamwork.project.utils.showErrorDialog
import kotlinx.android.synthetic.main.fragment_projest_list.*
import kotlinx.android.synthetic.main.fragment_projest_list.view.*
import javax.inject.Inject

class ProjectsListFragment : BaseFragment<ProjectsListViewModel>() {

    @Inject
    lateinit var dateUtils: DateUtils

    private val projectsAdapter: ProjectsAdapter by lazy { ProjectsAdapter(dateUtils) }

    override fun initVM(): ProjectsListViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ProjectsListViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadProjects()
    }

    override fun onResume() {
        super.onResume()

        setupAppBar()
    }

    private fun setupAppBar() {
        val parentActivity = activity
        if (parentActivity is AppCompatActivity) {
            parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            parentActivity.supportActionBar?.title = resources.getString(R.string.fragment_projects_list_title)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_projest_list, container, false)
        view.channelRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        view.channelRecyclerView.adapter = projectsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.projectsLiveData.observe(this, Observer {
            onProjectListStateUpdated(it)
        })
    }

    private fun onProjectListStateUpdated(projectListState: ProjectListState) {
        projectsAdapter.projectsList = projectListState.projects
        if (projectListState.loading) {
            channelProgressBar.visibility = View.VISIBLE
        } else {
            channelProgressBar.visibility = View.GONE
        }

        if (projectListState.error) {
            showErrorDialog()
        }

        projectsAdapter.projectSelectedLiveData.observe(this, Observer {
            startFragment(ProjectDetailsFragment.newInstance(it))
        })
    }

    companion object {

        fun newInstance(): ProjectsListFragment = ProjectsListFragment()
    }
}