package com.rtllabs.githubappexplore.gitsearchmodule.ui.searchList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rtllabs.githubappexplore.data.model.RepositoryItemGitSearch
import com.rtllabs.githubappexplore.data.repository.base.GitHubRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitSearchMainViewModel @Inject constructor(
    private val repository: GitHubRepoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: StateFlow<UiState>

    val pagingDataFlow: Flow<PagingData<UiModel>>

    val accept: (UiAction) -> Unit

    init {
        val initialQuery: String = "android"
        val lastQueryScrolled: String = "android"
        val actionStateFlow = MutableSharedFlow<UiAction>()
        val search=actionStateFlow.
            filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Search(query = initialQuery )) }

        state=search.map {
            UiState(it.query,
                lastQuery = lastQueryScrolled,
                hasNotScrolledForCurrentSearch = false)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = UiState()
        )

        //TODO ExperimentalCoroutinesApi::class) req for FlatmapLatest
        pagingDataFlow=search.flatMapLatest { searchRepo(queryString = it.query) }
            .cachedIn(viewModelScope)

        accept={ action->
            viewModelScope.launch { actionStateFlow.emit(action) }
        }
    }

    fun searchRepo(queryString:String):Flow<PagingData<UiModel>> =
        repository.getRepoSearchResult(query =queryString).also {
            Log.d("rammm", "searchRepo: $it")
        }.map {
            pagingData->pagingData.map { UiModel.RepoItem(it) }
        }


}

sealed class UiAction{
    data class Search(val query: String): UiAction()
}

data class UiState(
    val query: String = "",
    val lastQuery:String="",
    val hasNotScrolledForCurrentSearch: Boolean = false
)

sealed class UiModel{
    data class RepoItem(val repo: RepositoryItemGitSearch) : UiModel()
}