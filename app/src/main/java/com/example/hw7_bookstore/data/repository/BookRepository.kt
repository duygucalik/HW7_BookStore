package com.example.hw7_bookstore.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.hw7_bookstore.MainApplication
import com.example.hw7_bookstore.MainApplication.Companion.productService
import com.example.hw7_bookstore.data.model.Book
import com.example.hw7_bookstore.data.model.GetBookDetailResponse
import com.example.hw7_bookstore.data.model.GetBooksResponse
import com.example.hw7_bookstore.data.source.remote.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository (private val productService: ProductService){

    val booksLiveData= MutableLiveData<List<Book>?>(listOf())

    val booksDetailLiveData = MutableLiveData<Book?>()
    val errorMessageLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getBook(){
        MainApplication.productService?.getProducts()?.enqueue(object :
            Callback<GetBooksResponse> {
            override fun onResponse(call: Call<GetBooksResponse>, response: Response<GetBooksResponse>) {
                val result = response.body()?.books

                if (result.isNullOrEmpty().not()) {
                    booksLiveData.value = result as List<Book>?
                }else{
                    booksLiveData.value = null
                }
                loadingLiveData.value = false
            }

            override fun onFailure(call: Call<GetBooksResponse>, t: Throwable) {
                errorMessageLiveData.value = t.message.orEmpty()
                loadingLiveData.value = false
                Log.e("GetProducts", t.message.orEmpty())
            }
        })
    }
    fun getBookDetail(id: Int) {
        loadingLiveData.value = true
        productService.getBookDetail(id).enqueue(object : Callback<GetBookDetailResponse> {
            override fun onResponse(
                call: Call<GetBookDetailResponse>,
                response: Response<GetBookDetailResponse>
            ) {
                booksDetailLiveData.value = response.body()?.book
                loadingLiveData.value = false
            }

            override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                errorMessageLiveData.value = t.message.orEmpty()
                loadingLiveData.value = false
                Log.e("GetProducts", t.message.orEmpty())
            }
        })
    }
}