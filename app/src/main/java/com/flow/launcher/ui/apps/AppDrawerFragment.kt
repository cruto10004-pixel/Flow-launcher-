package com.flow.launcher.ui.apps

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flow.launcher.R
import com.flow.launcher.adapter.AppGridAdapter
import com.flow.launcher.animation.LaunchAnimator
import com.flow.launcher.model.AppInfo
import com.flow.launcher.util.AppLoader
import kotlinx.coroutines.launch

class AppDrawerFragment : Fragment() {

    private lateinit var adapter: AppGridAdapter
    private var allApps: List<AppInfo> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_app_drawer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AppGridAdapter { app, _ -> launchApp(app) }

        view.findViewById<RecyclerView>(R.id.apps_grid).apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = this@AppDrawerFragment.adapter
        }

        view.findViewById<EditText>(R.id.search_field).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().lowercase()
                adapter.submitList(
                    if (query.isEmpty()) allApps
                    else allApps.filter { it.label.lowercase().contains(query) }
                )
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        LaunchAnimator.animateDrawerOpen(view)
        loadApps()
    }

    private fun loadApps() {
        lifecycleScope.launch {
            allApps = AppLoader.loadApps(requireContext())
            adapter.submitList(allApps)
        }
    }

    private fun launchApp(app: AppInfo) {
        val intent = Intent().apply {
            setClassName(app.packageName, app.activityName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        LaunchAnimator.applyWindowTransition(requireActivity())
    }
}
