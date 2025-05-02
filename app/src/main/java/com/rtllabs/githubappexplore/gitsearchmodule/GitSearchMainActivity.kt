package com.rtllabs.githubappexplore.gitsearchmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rtllabs.githubappexplore.R
import com.rtllabs.githubappexplore.gitsearchmodule.ui.searchList.GitSearchMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitSearchMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_search_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GitSearchMainFragment.newInstance())
                .commitNow()
        }
    }
}