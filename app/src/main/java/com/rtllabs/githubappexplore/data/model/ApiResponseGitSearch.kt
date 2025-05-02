package com.rtllabs.githubappexplore.data.model

data class ApiResponseGitSearch<T>(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: T
)
