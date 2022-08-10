package com.example.dtabookstore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.SearchAdapter
import com.example.dtabookstore.data.DataSourceTimKiem
import com.example.dtabookstore.databinding.FragmentProfileBinding
import com.example.dtabookstore.databinding.FragmentSearchBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.TimSachRequest
import com.example.dtabookstore.network.TimSachRespond
import com.example.dtabookstore.network.UserProfileRespond
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchFragment : Fragment() {

    // View binding
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timKiemButton.setOnClickListener {

            val tuKhoa = binding.timKiemEditText.text.toString()
            val requestTimSachTheoTen = TimSachRequest(tuKhoa)

            // call api tìm kiếm theo tên sách
            try {
                Api.retrofitService.timSachTheoTen(requestTimSachTheoTen).enqueue(object :
                    Callback<List<TimSachRespond>> {

                    // nếu call api thành công
                    override fun onResponse(
                        call: Call<List<TimSachRespond>>,
                        response: Response<List<TimSachRespond>>
                    ) {
                        val body = response.body()

                        if (body != null) {

                            Log.d("SearchFragment", body.toString())

                            DataSourceTimKiem.books = body

                            Log.d("SearchFragment", DataSourceTimKiem.books.toString())

                            val timKiemRecyclerView = binding.timKiemRecyclerView
                            // set layoutManager
                            timKiemRecyclerView.layoutManager =
                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                            // set adapter
                            timKiemRecyclerView.adapter = SearchAdapter(requireContext())

                        } else {
                            Log.d("SearchFragment", "body rỗng")
                        }

                    }

                    // nếu call api thất bại
                    override fun onFailure(call: Call<List<TimSachRespond>>, t: Throwable) {
                        Snackbar.make(binding.timKiemButton, "Lỗi mạng!", Snackbar.LENGTH_LONG)
                            .show()
                        Log.d("SearchFragment", "Lỗi mạng!")
                        t.message?.let { Log.d("SearchFragment", it) }
                    }

                })

            } catch (e: Exception) {
                Log.d("SearchFragment", e.message.toString())
            }

        }

    }

}