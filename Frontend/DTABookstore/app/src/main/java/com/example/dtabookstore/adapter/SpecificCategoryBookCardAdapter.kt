package com.example.dtabookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dtabookstore.HomeFragmentDirections
import com.example.dtabookstore.R
import com.example.dtabookstore.SpecificCategoryFragmentDirections
import com.example.dtabookstore.network.Book
import java.text.NumberFormat

class SpecificCategoryBookCardAdapter(
    private val context: Context?,
    private val dataSet: List<Book>
) : RecyclerView.Adapter<SpecificCategoryBookCardAdapter.SpecificCategoryBookCardViewHolder>() {



    class SpecificCategoryBookCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var book_id: String = ""
        val book_image: ImageView = view.findViewById(R.id.book_image)
        val book_title: TextView = view.findViewById(R.id.book_title)
        val book_price: TextView = view.findViewById(R.id.book_price)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
                val action = SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToBookDetailFragment(bookId = book_id)

                val navController = Navigation.findNavController(view)

                navController.navigate(action)
            }
        }
    }



    override fun getItemCount(): Int = dataSet.size



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecificCategoryBookCardViewHolder {

        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.specific_category_item, parent, false)

        return SpecificCategoryBookCardViewHolder(adapterLayout)

    }



    override fun onBindViewHolder(holder: SpecificCategoryBookCardViewHolder, position: Int) {

        val item: Book = dataSet[position]

        holder.book_id = item.id

//        holder.book_image.setImageResource(item.imgSrc[0])
        val imgUri = item.imgSrc[0].toUri().buildUpon().scheme("http").build()
        holder.book_image.load(imgUri)

        holder.book_title.text = item.title

        val formattedPrice = NumberFormat.getCurrencyInstance().format(item.price)
        holder.book_price.text = formattedPrice

    }
}