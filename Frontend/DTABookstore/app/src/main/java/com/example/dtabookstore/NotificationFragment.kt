package com.example.dtabookstore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.NotificationAdapter
import com.example.dtabookstore.adapter.OrderCardAdapter
import com.example.dtabookstore.data.DataSourceNotification
import com.example.dtabookstore.data.DataSourceOrder
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.databinding.ActivityMainBinding
import com.example.dtabookstore.databinding.FragmentNotificationBinding
import com.example.dtabookstore.databinding.FragmentOrderBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.NotificationRequest
import com.example.dtabookstore.network.NotificationRespond
import com.example.dtabookstore.network.UserOrderRespond
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NoficationFragment : Fragment() {

    // View binding
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val notificationRecyclerView = binding.thongBaoRecyclerView

        notificationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()

        val userId = NotificationRequest(DataSourceUserDangNhap.userId)

        try {
            Api.retrofitService.getNotification(userId)
                .enqueue(object :
                    Callback<MutableList<NotificationRespond>> {

                    override fun onResponse(
                        call: Call<MutableList<NotificationRespond>>,
                        response: Response<MutableList<NotificationRespond>>
                    ) {

                        val body = response.body()!!

                        Log.d("NotificationFragment", body.toString())

                        val thongBaoRecyclerView = binding.thongBaoRecyclerView

                        DataSourceNotification.notifications = body.reversed().toMutableList()

                        thongBaoRecyclerView.adapter = NotificationAdapter(requireContext())

//                        // set badge
//                        var badge = ActivityMainBinding.bottomNavigation.getOrCreateBadge(R.id.notificationFragment)
//                        badge.isVisible = true
//                        badge.number = DataSourceNotification.notifications.size

                    }

                    override fun onFailure(call: Call<MutableList<NotificationRespond>>, t: Throwable) {
                        Snackbar.make(binding.thongBaoRecyclerView, "Lỗi mạng!", Snackbar.LENGTH_LONG)
                            .show()
                        Log.d("NotificationFragment", "Lỗi mạng!")
                        t.message?.let { Log.d("OrderFragment", it) }
                    }

                })

        } catch (e: Exception) {
            Log.d("NotificationFragment", e.message.toString())
        }
    }

    // reset view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}