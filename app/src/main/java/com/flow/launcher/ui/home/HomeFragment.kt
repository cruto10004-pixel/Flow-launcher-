package com.flow.launcher.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flow.launcher.R
import com.flow.launcher.animation.LaunchAnimator
import com.flow.launcher.ui.apps.AppDrawerFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.dock_area).setOnClickListener {
            openAppDrawer()
        }

        view.setOnTouchListener(SwipeUpGestureListener(requireContext()) {
            openAppDrawer()
        })
    }

    private fun openAppDrawer() {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.drawer_open_enter,
                R.anim.drawer_open_exit
            )
            .replace(R.id.fragment_container, AppDrawerFragment())
            .addToBackStack(null)
            .commit()
    }
}
