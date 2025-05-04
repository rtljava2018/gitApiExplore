package com.rtllabs.githubappexplore.data.remote

import com.rtllabs.githubappexplore.data.model.ApiResponseGitSearch
import com.rtllabs.githubappexplore.data.model.RepositoryItemGitSearch
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitRepoSearchApiService {
    @Headers("Accept: application/vnd.github+json", "Authorization: Bearer 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")
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiResponseGitSearch<List<RepositoryItemGitSearch>>
}