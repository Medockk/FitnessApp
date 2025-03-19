package com.example.fitnessapp.feature_app.presentation.TakePhoto

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.usecase.Compare.UploadPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class TakePhotoViewModel @Inject constructor(
    private val uploadPhotoUseCase: UploadPhotoUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TakePhotoState())
    val state: State<TakePhotoState> = _state

    fun onEvent(event: TakePhotoEvent) {
        when (event) {
            is TakePhotoEvent.TakePhoto -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(
                        showIndicator = true,
                        imageByteArray = _state.value.imageByteArray + event.photo.toByteArray()
                    )
                    try {
                        when (_state.value.imageByteArray.size) {
                            1 -> {
                                uploadPhotoUseCase(
                                    photo = _state.value.imageByteArray[0],
                                    category = GalleryData.FRONT_SIDE
                                )
                            }

                            2 -> {
                                uploadPhotoUseCase(
                                    photo = _state.value.imageByteArray[1],
                                    category = GalleryData.LEFT_SIDE
                                )
                            }

                            3 -> {
                                uploadPhotoUseCase(
                                    photo = _state.value.imageByteArray[2],
                                    category = GalleryData.REVERS_SIDE
                                )
                            }

                            else -> {
                                uploadPhotoUseCase(
                                    photo = event.photo.toByteArray(),
                                    category = GalleryData.RIGHT_SIDE
                                )
                            }
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            TakePhotoEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }

    private fun Bitmap.toByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}