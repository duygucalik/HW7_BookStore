package com.example.hw7_bookstore.data.source.remote

import com.example.hw7_bookstore.common.Constants
import com.example.hw7_bookstore.data.model.GetBookDetailResponse
import com.example.hw7_bookstore.data.model.GetBooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET(Constants.Endpoint.GET_BOOKS)
    fun getProducts(): Call<GetBooksResponse>

    @GET(Constants.Endpoint.GET_BOOK_DETAIL)
    fun getBookDetail(
        @Query("id") id: Int
    ): Call<GetBookDetailResponse>
}