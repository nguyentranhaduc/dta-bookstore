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
import com.example.dtabookstore.ShoppingCartFragment
import com.example.dtabookstore.ShoppingCartFragmentDirections
import com.example.dtabookstore.data.DataSourceBook
import com.example.dtabookstore.data.DataSourceBooks
import com.example.dtabookstore.data.DataSourceGioHang
import com.example.dtabookstore.network.Book
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text
import java.text.NumberFormat


class BookCardShoppingCartAdapter(
    private val context: Context?
) : RecyclerView.Adapter<BookCardShoppingCartAdapter.BookCardShoppingCartViewHolder>() {



    // class ViewHolder
    class BookCardShoppingCartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var book_id: String = ""
        val book_image: ImageView = view.findViewById(R.id.book_image)
        val book_title: TextView = view.findViewById(R.id.book_title)
        val book_price: TextView = view.findViewById(R.id.book_price)
        val book_soluong: TextInputLayout = view.findViewById(R.id.so_luong_textInputLayout)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
                val action = ShoppingCartFragmentDirections.actionShoppingCartFragmentToBookDetailFragment(bookId = book_id)

                val navController = Navigation.findNavController(view)

                navController.navigate(action)
            }
        }

    }




    override fun getItemCount(): Int = DataSourceGioHang.books.size



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookCardShoppingCartViewHolder {

        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.gio_hang_item, parent, false)

        return BookCardShoppingCartViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: BookCardShoppingCartViewHolder, position: Int) {

        val item: Map<String, String> = DataSourceGioHang.books[position]

        val book: Book? = DataSourceBooks.findABook(item["productId"].toString())

        holder.book_id = item["productId"].toString()

        if (book != null) {
//            holder.book_image.setImageResource(book.book_image[0])
            val imgUri = book.imgSrc[0].toUri().buildUpon().scheme("http").build()
            holder.book_image.load(imgUri)

            holder.book_title.text = book.title

            val formattedPrice = NumberFormat.getCurrencyInstance().format(book.price)
            holder.book_price.text = formattedPrice.toString()

            holder.book_soluong.editText?.setText(item["quantity"])
        }

    }
}