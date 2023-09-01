package com.example.hw7_bookstore.ui.Books

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hw7_bookstore.MainApplication
import com.example.hw7_bookstore.R
import com.example.hw7_bookstore.common.viewBinding
import com.example.hw7_bookstore.data.model.Book
import com.example.hw7_bookstore.data.model.GetBooksResponse
import com.example.hw7_bookstore.databinding.FragmentBooksBinding
import com.example.hw7_bookstore.ui.Books.adapter.BooksAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksFragment : Fragment(R.layout.fragment_books),BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentBooksBinding::bind)

    private val viewModel by viewModels<BooksViewModel>()

    private val booksAdapter by lazy { BooksAdapter(this) }

    /*private val booksAdapter by lazy { BooksAdapter(this) }*/

    var bestSellerBook : Book? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            binding.rvBooks.adapter = booksAdapter

        viewModel.getBook()

    }
    private fun observeData() {

        viewModel.booksLiveData.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                booksAdapter.submitList(list)
            } else {
                Snackbar.make(requireView(), "Empty List!", 1000).show()
            }
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, 1000).show()
        }
    }
    override fun onBookClick(id: Int){
        val action = BooksFragmentDirections.bookstodetail(id)
        findNavController().navigate(action)

    }
}