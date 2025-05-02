package com.rtllabs.githubappexplore.gitsearchmodule.ui.searchList

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.rtllabs.githubappexplore.R
import com.rtllabs.githubappexplore.databinding.FragmentGitsearchMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitSearchMainFragment : Fragment() {

    companion object {
        fun newInstance() = GitSearchMainFragment()
    }
    private lateinit var  _binding: FragmentGitsearchMainBinding ;

    private val viewModel: GitSearchMainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentGitsearchMainBinding.inflate(inflater,container,false)
        return _binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchListAdapter=SearchListAdapter()
        _binding.recyclerView.adapter=searchListAdapter
        lifecycleScope.launch {
//            viewModel.pagingDataFlow.collectLatest(searchListAdapter::submitData)
            viewModel.pagingDataFlow.collectLatest{
                //Log.e("eeeeeee", "onViewCreated: $it", )
                searchListAdapter.submitData(it)
            }
        }



    }

}