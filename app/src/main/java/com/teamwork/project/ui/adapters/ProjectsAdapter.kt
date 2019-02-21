package com.teamwork.project.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import com.teamwork.project.R
import com.teamwork.project.utils.DateUtils
import com.teamwork.project.utils.SingleLiveEvent
import com.teamwork.repositories.models.Project
import io.reactivex.subjects.PublishSubject

class ProjectsAdapter(val dateUtils: DateUtils) : androidx.recyclerview.widget.RecyclerView.Adapter<ProjectViewHolder>() {

    private val _projectSelectedLiveData = SingleLiveEvent<Project>()
    val projectSelectedLiveData: LiveData<Project>
        get() = _projectSelectedLiveData

    var projectsList = listOf<Project>()
        set(value) {
            if (value != field) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val channel = projectsList[position]
        holder.let { onBind(it, channel) }
    }

    override fun getItemCount(): Int = projectsList.size

    fun onBind(holder: ProjectViewHolder, project: Project?) {
        with(holder) {
            channelTitle.text = project?.name
            channelDescription.text = project?.description
            channelPublished.text = dateUtils.formatProjectDates(project?.createdOn, project?.endDate)
            try {
                Picasso.with(channelTitle.context).load(project?.logo).into(channelImage)
            } catch (exception: Exception) {
                Picasso.with(channelTitle.context)
                        .load(R.mipmap.ic_launcher)
                        .into(channelImage)
            }
            project?.let {
                viewItem.setOnClickListener { _projectSelectedLiveData.value = project }
            }
        }
    }
}

class ProjectViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val channelTitle = view.findViewById<TextView>(R.id.projectTitle)
    val channelPublished = view.findViewById<TextView>(R.id.date)
    val channelDescription = view.findViewById<TextView>(R.id.projectDescription)
    val channelImage = view.findViewById<ImageView>(R.id.projectLogo)
    val viewItem = view
}