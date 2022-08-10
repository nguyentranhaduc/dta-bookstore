package com.example.dtabookstore

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.BookCardShoppingCartAdapter
import com.example.dtabookstore.data.DataSourceBooks
import com.example.dtabookstore.data.DataSourceGioHang
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.databinding.FragmentHomeBinding
import com.example.dtabookstore.databinding.FragmentShoppingCartBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.Book
import com.example.dtabookstore.network.DonHang
import com.example.dtabookstore.network.DonHangRespond
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.NumberFormat

class ShoppingCartFragment : Fragment() {

    // View binding
    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // Hàm onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // lấy tham chiếu đến recyclerView trong trang giỏ hàng
        val gioHangRecyclerView = binding.gioHangRecyclerView
        // Gán LayoutManager
        gioHangRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        // Gán Adapter
        gioHangRecyclerView.adapter = BookCardShoppingCartAdapter(requireContext())


        if (DataSourceGioHang.books.isEmpty()) {
            binding.thongBaoChuaChonHangTextView.visibility = View.VISIBLE
        }



        binding.datHangButton.setOnClickListener {

            if (DataSourceGioHang.books.isNotEmpty()) {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Tiến hành đặt hàng")
                    .setMessage("Bạn có chắc chắn muốn đặt hàng?")
                    .setNegativeButton(resources.getString(R.string.profile_huy_bo)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.profile_dong_y)) { dialog, which ->
                        // Respond to positive button press

                        // lấy userId đã đăng nhập
                        val  userId = DataSourceUserDangNhap.userId
                        // lấy danh sách sản phẩm cùng số lượng hiện có trong giỏ hàng
                        val danhSachSanPhamVaSoLuong = DataSourceGioHang.books
                        // lấy tổng tiền từ giỏ hàng
                        val total = DataSourceGioHang.tongTien
                        // tạo một đối tượng DonHang mới để đưa vào api
                        val donHang = DonHang(userId, danhSachSanPhamVaSoLuong, total)


                        // call api đặt hàng
                        try {
                            Api.retrofitService.datHang(donHang).enqueue(object :
                                Callback<DonHangRespond> {

                                // nếu call api đặt hàng thành công
                                override fun onResponse(
                                    call: Call<DonHangRespond>,
                                    response: Response<DonHangRespond>
                                ) {

                                    val body = response.body()

                                    if (body != null) {

                                        MaterialAlertDialogBuilder(requireContext())
                                            .setTitle("Thành công")
                                            .setMessage(body.status)
                                            .setPositiveButton("Đồng ý") { dialog, which ->
                                                // Respond to positive button press
                                            }
                                            .show()

                                        DataSourceGioHang.books.clear()
                                        DataSourceGioHang.tongTien = 0

                                        val action = ShoppingCartFragmentDirections.actionShoppingCartFragmentSelf()

                                        val navController = Navigation.findNavController(view)

                                        navController.navigate(action)
                                    } else {
                                        Log.d("ShoppingCartFragment", "body null")
                                    }

                                }

                                // nếu call api đặt hàng thất bại
                                override fun onFailure(call: Call<DonHangRespond>, t: Throwable) {
                                    Snackbar.make(binding.datHangButton, "Lỗi mạng!", Snackbar.LENGTH_LONG).show()
                                }

                            })

                        } catch (e: Exception) {
                            Log.d("ShoppingCartFragment", e.message.toString())
                        }
                    }
                    .show()

            }

        }

    }

    override fun onResume() {
        super.onResume()

        val formattedPrice = NumberFormat.getCurrencyInstance().format(DataSourceGioHang.tongTien)
        binding.tongTienTextView.text = formattedPrice
    }


    // reset view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}