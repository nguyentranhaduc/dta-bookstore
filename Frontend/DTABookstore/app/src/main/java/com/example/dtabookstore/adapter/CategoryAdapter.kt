package com.example.dtabookstore.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.CategoryFragmentDirections
import com.example.dtabookstore.HomeFragmentDirections
import com.example.dtabookstore.R
import com.example.dtabookstore.ShoppingCartFragmentDirections
import com.example.dtabookstore.data.DataSourceTheLoai

class CategoryAdapter(
    private val context: Context?
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // class ViewHolder
    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var theLoaiTextView: TextView = view.findViewById(R.id.the_loai_textView)
        val cardView: CardView = view.findViewById(R.id.the_loai_cardView)

        init {
            view.setOnClickListener {
                // chuyển hướng đến thể loại cụ thể
                var doiSoTheLoaiCuThe: String = ""
                when (theLoaiTextView.text) {
                    "Sách thiếu nhi" -> {
                        doiSoTheLoaiCuThe = "sachthieunhi"
                    }
                    "Sách văn học" -> {
                        doiSoTheLoaiCuThe = "sachvanhoc"
                    }
                    "Sách kỹ năng sống" -> {
                        doiSoTheLoaiCuThe = "sachkynangsong"
                    }
                    "Sách kinh tế" -> {
                        doiSoTheLoaiCuThe = "sachkinhte"
                    }
                    "Sách tham khảo" -> {
                        doiSoTheLoaiCuThe = "sachthamkhao"
                    }
                    "Sách học ngoại ngữ" -> {
                        doiSoTheLoaiCuThe = "sachhocngoaingu"
                    }
                }
                val action =
                    CategoryFragmentDirections.actionCategoryFragmentToSpecificCategoryFragment(
                        specificCategory = doiSoTheLoaiCuThe as String
                    )

                val navController = Navigation.findNavController(view)

                navController.navigate(action)
            }
        }
    }


    override fun getItemCount(): Int = DataSourceTheLoai.categories.size


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {

        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)

        return CategoryViewHolder(adapterLayout)

    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val theLoai: String = DataSourceTheLoai.categories[position]

        holder.theLoaiTextView.text = theLoai

        // Set màu nền tùy vào thể loại
        when (theLoai) {
            "Sách thiếu nhi" -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#d81b60"))
            }
            "Sách văn học" -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#7cb342"))
            }
            "Sách kỹ năng sống" -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#00897b"))
            }
            "Sách kinh tế" -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#1e88e5"))
            }
            "Sách tham khảo" -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#5e35b1"))
            }
            "Sách học ngoại ngữ" -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#f4511e"))
            }
        }

    }
}