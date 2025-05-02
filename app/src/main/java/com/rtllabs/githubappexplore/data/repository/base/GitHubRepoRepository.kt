package com.rtllabs.githubappexplore.data.repository.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rtllabs.githubappexplore.data.model.RepositoryItemGitSearch
import com.rtllabs.githubappexplore.data.remote.GitRepoSearchApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitHubRepoRepository @Inject constructor(
    private val gitRepoSearchApiService: GitRepoSearchApiService
) {

    fun getRepoSearchResult(query:String):Flow<PagingData<RepositoryItemGitSearch>>{
        return  Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GitPagingSource( service = gitRepoSearchApiService,
                    query = query)
            }

        ).flow
    }

    companion object{
        const val NETWORK_PAGE_SIZE=30
    }
}