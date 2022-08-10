package com.example.dtabookstore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.CategoryAdapter
import com.example.dtabookstore.adapter.OrderCardAdapter
import com.example.dtabookstore.data.DataSourceOrder
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.databinding.FragmentCategoryBinding
import com.example.dtabookstore.databinding.FragmentOrderBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.UserOrderRespond
import com.example.dtabookstore.network.UserProfile
import com.example.dtabookstore.network.UserUpdateInformationRespond
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class OrderFragment : Fragment() {

    // View binding
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val donHangRecyclerView = binding.donHangRecyclerView

        donHangRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }


    override fun onResume() {
        super.onResume()

        val userId = UserProfile(DataSourceUserDangNhap.userId)

        try {
            Api.retrofitService.getUserOrders(userId)
                .enqueue(object :
                    Callback<MutableList<UserOrderRespond>> {

                    override fun onResponse(
                        call: Call<MutableList<UserOrderRespond>>,
                        response: Response<MutableList<UserOrderRespond>>
                    ) {

                        val body = response.body()!!

                        Log.d("OrderFragment", body.toString())

                        DataSourceOrder.orders = body

                        DataSourceOrder.orders.reverse()

                        val donHangRecyclerView = binding.donHangRecyclerView

                        donHangRecyclerView.adapter = OrderCardAdapter(requireContext())

                    }

                    override fun onFailure(call: Call<MutableList<UserOrderRespond>>, t: Throwable) {
                        Snackbar.make(binding.donHangRecyclerView, "Lỗi mạng!", Snackbar.LENGTH_LONG)
                            .show()
                        Log.d("OrderFragment", "Lỗi mạng!")
                        t.message?.let { Log.d("OrderFragment", it) }
                    }

                })

        } catch (e: Exception) {
            Log.d("OrderFragment", e.message.toString())
        }

    }


    // reset view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}