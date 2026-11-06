package com.flow.launcher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flow.launcher.R
import com.flow.launcher.animation.LaunchAnimator
import com.flow.launcher.model.AppInfo

class AppGridAdapter(
    private val onAppClick: (AppInfo, View) -> Unit
) : ListAdapter<AppInfo, AppGridAdapter.AppViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.app_icon)
        private val label: TextView = itemView.findViewById(R.id.app_label)

        fun bind(app: AppInfo) {
            icon.setImageDrawable(app.icon)
            label.text = app.label

            itemView.setOnClickListener {
                LaunchAnimator.animateAppOpen(itemView) {
                    onAppClick(app, itemView)
                    LaunchAnimator.animateAppClose(itemView)
                }
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<AppInfo>() {
            override fun areItemsTheSame(a: AppInfo, b: AppInfo) =
                a.packageName == b.packageName
            override fun areContentsTheSame(a: AppInfo, b: AppInfo) =
                a.label == b.label
        }
    }
}
