package com.example.dtabookstore.adapter

import android.app.Notification
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.R
import com.example.dtabookstore.data.DataSourceNotification
import com.example.dtabookstore.network.NotificationRespond

class NotificationAdapter(private val context: Context?) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    // class viewHolder
    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_textView)
        val thoiGian: TextView = view.findViewById(R.id.thoi_gian_textView)
        val noiDung: TextView = view.findViewById(R.id.noi_dung_textView)
    }

    override fun getItemCount(): Int {
        return DataSourceNotification.notifications.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)

        return NotificationViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification: NotificationRespond = DataSourceNotification.notifications[position]

        holder.title.text = notification.title
        holder.thoiGian.text = notification.date
        holder.noiDung.text = notification.content
    }
}