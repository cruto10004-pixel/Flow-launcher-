package com.flow.launcher

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.flow.launcher.ui.home.HomeFragment

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_launcher)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }

    // Намеренно не вызываем super: на главном экране «Назад» ничего не делает,
    // иначе Activity лаунчера завершится.
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (current !is HomeFragment) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.drawer_close_enter,
                    R.anim.drawer_close_exit
                )
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}
