package com.example.hw7_bookstore.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw7_bookstore.data.model.Book
import com.example.hw7_bookstore.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val booksRepository:BookRepository): ViewModel() {


    private var _productDetailLiveData = MutableLiveData<Book?>()
    val productDetailLiveData: LiveData<Book?>
        get() = _productDetailLiveData

    private var _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        _productDetailLiveData = booksRepository.booksDetailLiveData
        _errorMessageLiveData = booksRepository.errorMessageLiveData
        _loadingLiveData = booksRepository.loadingLiveData
    }



    fun getBookDetail(id:Int){
        booksRepository.getBookDetail(id)
    }
}