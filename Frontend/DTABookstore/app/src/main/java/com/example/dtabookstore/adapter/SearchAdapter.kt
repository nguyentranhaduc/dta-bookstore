package com.example.dtabookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.HomeFragmentDirections
import com.example.dtabookstore.R
import com.example.dtabookstore.SearchFragmentDirections
import com.example.dtabookstore.data.DataSourceTimKiem
import com.example.dtabookstore.network.TimSachRespond

class SearchAdapter(private val context: Context?) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    // class viewHolder
    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bookId: String = ""
        val title: TextView = view.findViewById(R.id.ten_sach_textView)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToBookDetailFragment(bookId = bookId)

                val navController = Navigation.findNavController(view)

                navController.navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return DataSourceTimKiem.books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)

        return SearchViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val sach: TimSachRespond = DataSourceTimKiem.books[position]

        holder.bookId = sach.productId.toString()

        holder.title.text = sach.title
    }
}