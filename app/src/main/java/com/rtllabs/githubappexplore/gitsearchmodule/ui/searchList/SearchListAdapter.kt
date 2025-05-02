package com.rtllabs.githubappexplore.gitsearchmodule.ui.searchList

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rtllabs.githubappexplore.R

class SearchListAdapter() : PagingDataAdapter<UiModel, ViewHolder>(
    UIMODEL_COMPARATOR
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val uiModel=getItem(position)
       // Log.e("eeee", "onBindViewHolder: "+uiModel )
        uiModel.let {
            when (uiModel) {
                is UiModel.RepoItem -> (holder as RepoItemViewHolder).bind(uiModel.repo)
                null -> throw UnsupportedOperationException("Unknown view")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return RepoItemViewHolder.create(parent)
        /* if (viewType == R.layout.repo_list_items){
            RepoItemViewHolder.create(parent)
        }else{
            //
        }*/
    }

    override fun getItemViewType(position: Int): Int {
        //TODo
        return R.layout.repo_list_items
    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.RepoItem && newItem is UiModel.RepoItem &&
                        oldItem.repo.fullName == newItem.repo.fullName) /*||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem &&
                                oldItem.description == newItem.description)*/
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                oldItem == newItem
        }
    }
}