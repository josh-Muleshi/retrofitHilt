package com.example.retrofithilt.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofithilt.util.DispatcherProvider
import com.example.retrofithilt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.w3c.dom.Comment
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel(){
    /*sealed class CurrencyComment{
        class Success(val resultText: String): CurrencyComment()
        class Failure(val errorText: String): CurrencyComment()
        object Loading: CurrencyComment()
        object Empty: CurrencyComment()
    }

    private val _conversion = MutableStateFlow<CurrencyComment>(CurrencyComment.Empty)
    val conversion: StateFlow<CurrencyComment> = _conversion

    fun convert(){
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyComment.Loading
            when(val commentsResponse = repository.getComments()){
                is Resource.Error -> _conversion.value = CurrencyComment.Failure(commentsResponse.message!!)
                is Resource.Success -> {
                    val c = commentsResponse.data
                    _conversion.value = c
                }
            }
        }
    }*/

    private lateinit var job: Job

    private val _comments = MutableLiveData<List<Comment>>()
    val comments : LiveData<List<Comment>>
        get() = _comments

    suspend fun getComments(){
        job = Coroutines.ioThenMain(
            { repository.getComments() },
            { _comments.value = it }
        )
    }

}