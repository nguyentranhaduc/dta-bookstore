package com.example.dtabookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.R
import com.example.dtabookstore.data.DataSourceOrderDetail

class OrderDetailCardAdapter(private val context: Context?) : RecyclerView.Adapter<OrderDetailCardAdapter.OrderDetailViewHolder>() {

    // class viewHolder
    class OrderDetailViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val maDonHang: TextView = view.findViewById(R.id.ma_don_hang_textView)
        val soLuong: TextView = view.findViewById(R.id.so_luong_textView)
    }

    override fun getItemCount(): Int {
        return DataSourceOrderDetail.products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.order_detail_item, parent, false)

        return OrderDetailViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        val orderDetailItem: Map<String, String> = DataSourceOrderDetail.products[position]

        holder.maDonHang.text = orderDetailItem["productId"]
        holder.soLuong.text = orderDetailItem["quantity"]
    }


}