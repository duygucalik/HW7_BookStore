package com.example.hw7_bookstore.data.model

data class GetBooksResponse(

    val books: List<Book?>?,
    val message: String?,
    val success: Int?
)
