package com.example.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogallery.models.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryViewModel : ViewModel() {
    private val repository = PhotoRepository()
    private val preferencesRepository = PreferencesRepository.get()
    private val _uiState: MutableStateFlow<PhotoGalleryState> = MutableStateFlow(PhotoGalleryState())
    val uiState: StateFlow<PhotoGalleryState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.storedQuery.collectLatest { storedQuery ->
                try {
                    val items = fetchPhotos(storedQuery)
                    _uiState.update { oldState ->
                        oldState.copy(
                            images = items,
                            query = storedQuery
                        )
                    }
                } catch (ex: Exception) {
                    Log.d(TAG, "Failed request", ex)
                }
            }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            preferencesRepository.setSearchQuery(query)
        }
    }

    private suspend fun fetchPhotos(query: String): List<Photo> {
        return if (query.isNotEmpty()) {
            repository.searchPhoto(query)
        } else {
            repository.fetchContents()
        }
    }
}

data class PhotoGalleryState(
    val images: List<Photo> = listOf(),
    val query: String = ""
)