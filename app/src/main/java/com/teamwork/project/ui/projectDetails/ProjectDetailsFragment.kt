package com.teamwork.project.ui.projectDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.teamwork.project.ui.BaseFragment
import com.teamwork.project.utils.DateUtils
import com.teamwork.repositories.models.Project
import kotlinx.android.synthetic.main.fragment_project_details.*
import kotlinx.android.synthetic.main.fragment_project_details.view.*
import javax.inject.Inject
import android.view.MenuItem

class ProjectDetailsFragment : BaseFragment<ProjectDetailsViewModel>() {

    private var detailsView: View? = null

    @Inject
    lateinit var dateUtils: DateUtils

    override fun initVM(): ProjectDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ProjectDetailsViewModel::class.java)

    companion object {
        private const val PROJECT_ID_KEY = "projectId"
        private const val PROJECT_NAME_KEY = "projectName"

        fun newInstance(project: Project): ProjectDetailsFragment {
            val projectDetailsFragment = ProjectDetailsFragment()
            val bundle = Bundle()
            bundle.putString(PROJECT_ID_KEY, project.id)
            bundle.putString(PROJECT_NAME_KEY, project.name)
            projectDetailsFragment.arguments = bundle
            return projectDetailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailsView = inflater.inflate(com.teamwork.project.R.layout.fragment_project_details, container, false)
        return detailsView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            if (bundle.containsKey(PROJECT_ID_KEY)
                && bundle.containsKey(PROJECT_NAME_KEY)) {
                viewModel.loadProject(bundle.getString(PROJECT_ID_KEY)!!)
                setupAppBar(bundle.getString(PROJECT_NAME_KEY))
            }
        }

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar
        }
    }

    private fun setupAppBar(projectName: String?) {
        val parentActivity = activity
        if (parentActivity is AppCompatActivity) {
            parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            parentActivity.supportActionBar?.title = projectName
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.projectLiveData.observe(this, Observer {
            onProjectStateUpdated(it)
        })
    }

    private fun onProjectStateUpdated(projectDetailsState: ProjectDetailsState) {
        if (projectDetailsState.loading) {
            channelProgressBar.visibility = View.VISIBLE
        } else {
            channelProgressBar.visibility = View.GONE
        }

        if (projectDetailsState.error) {
            view?.error?.visibility = View.VISIBLE
        } else {
            view?.error?.visibility = View.GONE
        }

        projectDetailsState.project?.let {
            loadLogo(it.logo)
            view?.companyName?.text = it.company.name
            view?.dates?.text = dateUtils.formatProjectDates(it.createdOn, it.endDate)
            view?.projectDescription?.text = it.description
        }

    }

    private fun loadLogo(logoUrl: String?) {
        try {
            Picasso.with(activity).load(logoUrl).into(view?.projectLogo)
        } catch (exception: Exception) {
            Picasso.with(activity)
                .load(com.teamwork.project.R.mipmap.ic_launcher)
                .into(view?.projectLogo)
        }
    }
}