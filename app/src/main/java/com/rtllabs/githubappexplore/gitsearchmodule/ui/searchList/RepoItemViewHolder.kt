package com.rtllabs.githubappexplore.gitsearchmodule.ui.searchList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rtllabs.githubappexplore.R
import com.rtllabs.githubappexplore.data.model.RepositoryItemGitSearch

class RepoItemViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val name:TextView=view.findViewById(R.id.user)
    private val description:TextView=view.findViewById(R.id.description)
    private val language:TextView=view.findViewById(R.id.language)
//    private val fork:TextView=view.findViewById(R.id.user)

    @SuppressLint("SetTextI18n")
    fun bind(repo:RepositoryItemGitSearch?){
        //TODO
        if (repo !=null){
            name.text=repo.fullName
            description.text=repo.description
            language.text=repo.language
        }else{
            name.text="loading.."
            description.text="loading.."
            language.text="loading.."
        }
    }

    companion object{
        fun create(parent: ViewGroup):RepoItemViewHolder{
            val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_list_items,parent,false)
            return RepoItemViewHolder(view)
        }
    }
}