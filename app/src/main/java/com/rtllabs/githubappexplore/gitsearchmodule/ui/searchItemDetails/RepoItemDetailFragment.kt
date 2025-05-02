package com.rtllabs.githubappexplore.gitsearchmodule.ui.searchItemDetails

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtllabs.githubappexplore.R

class RepoItemDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RepoItemDetailFragment()
    }

    private val viewModel: RepoItemDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_repo_item_detail, container, false)
    }
}