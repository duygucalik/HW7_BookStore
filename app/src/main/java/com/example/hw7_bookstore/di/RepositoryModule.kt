package com.example.hw7_bookstore.di

import com.example.hw7_bookstore.data.repository.BookRepository
import com.example.hw7_bookstore.data.source.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(productService: ProductService): BookRepository =
        BookRepository(productService)
}