package com.acon.feature.upload

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acon.domain.repository.UploadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val uploadRepository: UploadRepository
) : ViewModel(),
    ContainerHost<UploadState, UploadSideEffect> {
    override val container = container<UploadState, UploadSideEffect>(UploadState())

    val searchFlow = MutableStateFlow("")

    init {
        getDotoriCount()
        observeSearch()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearch() = intent {
        searchFlow
            .debounce(300L)
            .distinctUntilChanged()
            .onEach { keyword ->
                if (keyword.isEmpty()) {
                    reduce { state.copy(searchResults = persistentListOf()) }
                } else {
                    uploadRepository.getKeyWord(keyword)
                        .onSuccess { keyWord ->
                            reduce {
                                state.copy(searchResults = keyWord.spotList.toPersistentList())
                            }
                        }
                        .onFailure { error ->
                            Log.d("search", "장소 검색 에러: ${error.message}")
                            reduce { state.copy(searchResults = persistentListOf()) }
                        }
                }
            }
            .launchIn(viewModelScope)
    }


    fun onIntent(intent: UploadIntent) {
        when (intent) {
            is UploadIntent.SelectLocation -> {
                intent {
                    reduce { state.copy(selectedLocation = intent.location) }
                }
            }

            is UploadIntent.OnNextStep -> {
                intent {
                    getDotoriCount()
                    reduce { state.copy(currentStep = UploadStep.UPLOAD_REVIEW) }
                }
            }

            is UploadIntent.SelectDotori -> selectDotori(intent.index)
            is UploadIntent.DeselectDotori -> deselectDotori(intent.index)
            is UploadIntent.NavigateBack -> navigateBack()
            is UploadIntent.UploadDotori -> uploadDotori()
            is UploadIntent.LoadSuggestions -> loadSuggestions(intent.latitude, intent.longitude)
        }
    }

    private fun selectDotori(index: Int) = intent {
        if (index >= state.selectedCount && index < state.maxCount) {
            reduce {
                state.copy(animatingIndex = null)
            }

            delay(50)
            reduce {
                state.copy(
                    selectedCount = index + 1,
                    isButtonEnabled = true,
                    animatingIndex = index
                )
            }

            delay(3000)
            reduce {
                if (state.animatingIndex == index) {
                    state.copy(animatingIndex = null)
                } else {
                    state
                }
            }
        }
    }

    private fun deselectDotori(index: Int) = intent {
        if (state.selectedCount > 0) {
            reduce {
                state.copy(animatingIndex = null)
            }

            delay(50)

            reduce {
                state.copy(
                    selectedCount = index + 1,
                    isButtonEnabled = true,
                    animatingIndex = index
                )
            }

            delay(3000)
            reduce {
                if (state.animatingIndex == index) {
                    state.copy(animatingIndex = null)
                } else {
                    state
                }
            }
        }
    }

    private fun getDotoriCount() = intent {
        uploadRepository.getDotoriCount()
            .onSuccess { dotoriCount ->
                reduce {
                    state.copy(ownedDotoriCount = dotoriCount.count)
                }
            }
            .onFailure { error ->
                Log.d("dotori","도토리 카운트 에러: ${error.message}")
            }
    }


    private fun loadSuggestions(latitude: Double, longitude: Double) = intent {
        Log.d("LocationSearch", "loadSuggestions 호출됨: lat=$latitude, lon=$longitude")
        uploadRepository.getSuggestions(latitude, longitude)
            .onSuccess { suggestions ->
                Log.d("LocationSearch", "추천 검색어 로드 성공: ${suggestions.suggestionList.size}개")
                reduce {
                    state.copy(suggestions = suggestions.suggestionList.toPersistentList())
                }
            }
            .onFailure { error ->
                Log.d("LocationSearch", "추천 검색어 로드 실패: ${error.message}")
                reduce {
                    state.copy(suggestions = persistentListOf())
                }
            }
    }

    private fun uploadDotori() = intent {
        postSideEffect(UploadSideEffect.NavigateToSuccess)
    }

    private fun navigateBack() = intent {
        postSideEffect(UploadSideEffect.NavigateBack)
    }
}
