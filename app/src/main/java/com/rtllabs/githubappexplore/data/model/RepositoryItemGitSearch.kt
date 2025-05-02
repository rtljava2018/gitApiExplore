package com.rtllabs.githubappexplore.data.model

data class RepositoryItemGitSearch(
    val id: Long,
    val name: String? = "",
    val fullName: String? = "",
    val owner: Owner,
    val html_url: String? = "",
    val description: String? = "",
    val language: String? = "",
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val topics: List<String>,
    val createdAt: String? = "",
    val updatedAt: String? = ""
)

data class Owner(
    val login: String? = "",
    val avatar_url: String? = ""
)
