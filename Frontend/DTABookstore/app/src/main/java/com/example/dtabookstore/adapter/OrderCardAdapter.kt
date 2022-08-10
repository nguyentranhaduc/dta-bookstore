package com.example.dtabookstore.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.CategoryFragmentDirections
import com.example.dtabookstore.OrderFragment
import com.example.dtabookstore.OrderFragmentDirections
import com.example.dtabookstore.R
import com.example.dtabookstore.data.DataSourceOrder
import com.example.dtabookstore.model.Order
import com.example.dtabookstore.network.UserOrderRespond
import java.text.NumberFormat

class OrderCardAdapter(
    private val context: Context?
) : RecyclerView.Adapter<OrderCardAdapter.OrderCardViewHolder>() {

    class OrderCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var order_id: String = ""
        val trangThaiGiaoHang: TextView = view.findViewById(R.id.trang_thai_giao_hang_textView)
        val orderId: TextView = view.findViewById(R.id.ma_don_hang_textView)
        val tongTien: TextView = view.findViewById(R.id.tong_tien_textView)
        val xemDonHang: Button = view.findViewById(R.id.xem_don_hang_button)
    }



    override fun getItemCount(): Int = DataSourceOrder.orders.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderCardViewHolder {

        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)

        return OrderCardViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: OrderCardViewHolder, position: Int) {

        val item: UserOrderRespond = DataSourceOrder.orders[position]

        holder.order_id = item.orderId.toString()

        when (item.status) {
            "choxacnhandonhang" -> {
                holder.trangThaiGiaoHang.text = "Chờ xác nhận đơn hàng"
                holder.trangThaiGiaoHang.setTextColor(Color.parseColor("#afb42b"))
            }
            "daxacnhan" -> {
                holder.trangThaiGiaoHang.text = "Đơn hàng đã được xác nhận"
                holder.trangThaiGiaoHang.setTextColor(Color.parseColor("#0097a7"))
            }
            "danggiao" -> {
                holder.trangThaiGiaoHang.text = "Đơn hàng đang được giao"
                holder.trangThaiGiaoHang.setTextColor(Color.parseColor("#e65100"))
            }
            "dagiao" -> {
                holder.trangThaiGiaoHang.text = "Đơn hàng đã được giao thành công"
                holder.trangThaiGiaoHang.setTextColor(Color.parseColor("#33691e"))
            }
            else -> {
                holder.trangThaiGiaoHang.text = "Lỗi trạng thái"
                holder.trangThaiGiaoHang.setBackgroundResource(R.color.red_600)
            }
        }

        holder.orderId.text = item.orderId

        val formattedPrice = NumberFormat.getCurrencyInstance().format(item.total)
        holder.tongTien.text = formattedPrice


        holder.xemDonHang.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderFragmentToOrderDetailFragment(maDonHang = item.orderId.toString())

            val navController = Navigation.findNavController(holder.xemDonHang)

            navController.navigate(action)
        }
    }

}