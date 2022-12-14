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


    // override ham onBindViewHolder - ???????c g???i b???i layoutManager ????? m?? thay th??? n???i dung c???a m???t itemView
    // C?? 2 tham s???:
    //      1. L?? ItemViewHolder ???? ???????c t???o tr?????c ???? b???i h??m onCreateViewHolder
    //      2. M???t s??? Int ?????i di???n cho v??? tr?? hi???n t???i c???a item trong list.
    // Trong h??m n??y ta s??? t??m ????ng ?????i t?????ng Book trong dataset d???a tr??n v??? tr??
    override fun onBindViewHolder(holder: BookCardViewHolder, position: Int) {
        // T???o m???t val item v?? l???y ?????i t?????ng Book b???ng v??? tr?? ???? cho trong dataset.
        val item: Book = dataSet[position]

        // Sau ???? ch??ng ta c???n c???p nh???t m???i tham chi???u View th??ng qua ViewHolder ????? ??nh x??? d??? li???u cho item n??y.
        holder.book_id = item.book_id
        holder.book_image.setImageResource(item.book_image[0]) // set h??nh ???nh cho s??ch
        holder.book_title.text = item.book_title // set t??n s??ch

        // set gi?? s??ch
        val formattedPrice = NumberFormat.getCurrencyInstance().format(item.book_price)
        holder.book_price.text = formattedPrice // set gi?? s??ch

        // - M???t c??ch kh??c ????? l???y m???t chu???i string t??? m???t string source id. Ta c?? th??? s??? d???ng h??m getString() v???i m???t string resource ID
        // v?? tr??? v??? m???t gi?? tr??? ???????c g???n k???t v???i n??.
        // - H??m getString() l?? m???t ph????ng th???c trong class Resources v?? ta c?? th??? l???y n?? th??ng qua ?????i t?????ng context.
        // - V?? d???: holder.textView.text = context.resources.getString(item.stringResourceId).
    }


}