package com.rtllabs.githubappexplore.data.repository.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rtllabs.githubappexplore.data.model.RepositoryItemGitSearch
import com.rtllabs.githubappexplore.data.remote.GitRepoSearchApiService
import com.rtllabs.githubappexplore.data.repository.base.GitHubRepoRepository.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

class GitPagingSource(
    private val service: GitRepoSearchApiService,
    private val query:String
) : PagingSource<Int, RepositoryItemGitSearch>() {
    override fun getRefreshKey(state: PagingState<Int, RepositoryItemGitSearch>): Int? {

        return  state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItemGitSearch> {
        val position=params.key?: 1
        val apiQuery=query
        return try {
            val result=service.searchRepositories(
                apiQuery,
                1,
                params.loadSize
            )
            val response=result.items
            val nextKey= if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = response,
                prevKey = if (position == 1 ) null else position-1,
                nextKey = nextKey
            )
        }catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}