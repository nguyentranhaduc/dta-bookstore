package com.example.dtabookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.HomeFragment
import com.example.dtabookstore.HomeFragmentDirections
import com.example.dtabookstore.R
import com.example.dtabookstore.model.Book
//import com.example.dtabookstore.model.Book

import org.w3c.dom.Text
import java.text.NumberFormat

class BookCardAdapter(

    // context: thong tin cac resources, cac thong tin ve resource dc luu tru trong mot the hien Context
    private val context: Context?,

    // dataSet sach - Book
    private val dataSet: List<Book>

) : RecyclerView.Adapter<BookCardAdapter.BookCardViewHolder>() {

    // class view holder, mot viewHolder dai dien cho 1 item view holder trong recyclerview
    class BookCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // tao cac doi tuong tham chiu den cac thanh phan trong itemView
        var book_id: String = ""
        val book_image: ImageView = view.findViewById(R.id.book_image)
        val book_title: TextView = view.findViewById(R.id.book_title)
        val book_price: TextView = view.findViewById(R.id.book_price)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToBookDetailFragment(bookId = book_id)

                val navController = Navigation.findNavController(view)

                navController.navigate(action)
            }
        }

    }


    //---------------- Override cac ham can thiet cho mot adapter class ------------------------------------------------------


    // override ham getItemCount - Cach ngan gon de lay so luong cac item
    override fun getItemCount(): Int = dataSet.size


    // override ham onCreateViewHolder - duoc goi boi layoutManager de tao cac viewHolder cho RecyclerView
    // lay 2 tham so va tra ve mot ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCardViewHolder {
        // parent: la parent ma itemViewHolder se duoc gan vao, o day la RecyclerView
        // viewType: cho biet cach sap xep RecyclerView


        /*
            - Tao mot bien adapterLayout  de chua context cua parent de cho inflater biet cach thoi phong mot XML layout vao trong
        cay phan cap cua cac doi tuong View.
            - Sau do goi ham inflate de thoi phong itemView, truyen vao do layout resource id va cai parent view group cua no,
        tham so thu 3 la "attachToRoot", se gan gia tri false vi RecyclerView se them item nay vao khi den luc can thiet.
         */
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)

        // Bay gio AdapterLayout da co mot tham chieu den itemView, cai ma sau do chung ta co the tim cac View con  nhu TextView


        // tra ve mot the hien ViewHolder moi noi ma root view la adapterLayout.
        return BookCardViewHolder(adapterLayout)
    }


    // override ham onBindViewHolder - được gọi bởi layoutManager để mà thay thế nội dung của một itemView
    // Có 2 tham số:
    //      1. Là ItemViewHolder đã được tạo trước đó bởi hàm onCreateViewHolder
    //      2. Một số Int đại diện cho vị trí hiện tại của item trong list.
    // Trong hàm này ta sẽ tìm đúng đối tượng Book trong dataset dựa trên vị trí
    override fun onBindViewHolder(holder: BookCardViewHolder, position: Int) {
        // Tạo một val item và lấy đối tượng Book bằng vị trí đã cho trong dataset.
        val item: Book = dataSet[position]

        // Sau đó chúng ta cần cập nhật mọi tham chiếu View thông qua ViewHolder để ánh xạ dữ liệu cho item này.
        holder.book_id = item.book_id
        holder.book_image.setImageResource(item.book_image[0]) // set hình ảnh cho sách
        holder.book_title.text = item.book_title // set tên sách

        // set giá sách
        val formattedPrice = NumberFormat.getCurrencyInstance().format(item.book_price)
        holder.book_price.text = formattedPrice // set giá sách

        // - Một cách khác để lấy một chuỗi string từ một string source id. Ta có thể sử dụng hàm getString() với một string resource ID
        // và trả về một giá trị được gắn kết với nó.
        // - Hàm getString() là một phương thức trong class Resources và ta có thể lấy nó thông qua đối tượng context.
        // - Ví dụ: holder.textView.text = context.resources.getString(item.stringResourceId).
    }


}