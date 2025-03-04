package com.example.fitnessapp.feature_app.presentation.TakePhoto

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.usecase.Compare.UploadPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class TakePhotoViewModel(
    private val uploadPhotoUseCase: UploadPhotoUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TakePhotoState())
    val state: State<TakePhotoState> = _state

    fun onEvent(event: TakePhotoEvent){
        when (event){
            is TakePhotoEvent.TakePhoto -> {
                if (_state.value.imageByteArray.size < 5) {
                    _state.value = state.value.copy(
                        imageByteArray = _state.value.imageByteArray + event.photo.toByteArray()
                    )

                    _state.value = state.value.copy(showIndicator = true)
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            when (_state.value.imageByteArray.size){
                                1 -> {
                                    uploadPhotoUseCase(
                                        photo = _state.value.imageByteArray[0],
                                        category = GalleryData.frontSide
                                    )
                                }
                                2 -> {
                                    uploadPhotoUseCase(
                                        photo = _state.value.imageByteArray[1],
                                        category = GalleryData.leftSide
                                    )
                                }
                                3 -> {
                                    uploadPhotoUseCase(
                                        photo = _state.value.imageByteArray[2],
                                        category = GalleryData.reverseSide
                                    )
                                }
                                4 -> {
                                    uploadPhotoUseCase(
                                        photo = _state.value.imageByteArray[3],
                                        category = GalleryData.rightSide
                                    )
                                }
                            }
                        } catch (e: Exception) {
                            _state.value = state.value.copy(exception = e.message.toString())
                        }
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            TakePhotoEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }

    private fun Bitmap.toByteArray() : ByteArray{
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}