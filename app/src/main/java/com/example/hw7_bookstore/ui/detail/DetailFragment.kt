package com.example.hw7_bookstore.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hw7_bookstore.R
import com.example.hw7_bookstore.common.loadImage
import com.example.hw7_bookstore.common.viewBinding
import com.example.hw7_bookstore.databinding.FragmentDetailBinding
import com.example.hw7_bookstore.ui.Books.BooksViewModel
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBookDetail(args.id)

        observeData()
    }

    private fun observeData() = with(binding) {


           viewModel.productDetailLiveData.observe(viewLifecycleOwner){Book ->
               if (Book != null) {
                   tvName.text = Book.name
                   tvAuthor.text = "Yazar: ${Book.author}"
                   tvPublisher.text = "Yayımcı: ${Book.publisher}"
                   tvPrice.text = "Fiyat: ${Book.price} ₺"
                   ivBook.loadImage(Book.imageUrl)
               } else {
                   Snackbar.make(requireView(), "Product not found!", 1000).show()
               }

           }
            viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
                Snackbar.make(requireView(), it, 1000).show()

        }

    }
}