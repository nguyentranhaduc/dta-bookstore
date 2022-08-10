package com.example.dtabookstore

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.NotificationAdapter
import com.example.dtabookstore.adapter.OrderDetailCardAdapter
import com.example.dtabookstore.adapter.SpecificCategoryBookCardAdapter
import com.example.dtabookstore.data.DataSourceOrderDetail
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.databinding.FragmentOrderDetailBinding
import com.example.dtabookstore.databinding.FragmentSpecificCategoryBinding
import com.example.dtabookstore.network.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.NumberFormat

class OrderDetailFragment : Fragment() {

    // View binding
    private var _binding: FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!



    companion object{
        // action args
        val ORDER_DETAIL = "ma_don_hang"
    }

    private lateinit var maDonHang: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            maDonHang = it.getString(OrderDetailFragment.ORDER_DETAIL).toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set title cho topappbar
        binding.topAppBar.title = "Đơn hàng " + maDonHang

        // set sự kiện click cho nút hủy đơn hàng
        binding.huyDonHangButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn hủy đơn hàng này?")
                .setNegativeButton("Hủy bỏ") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("Đồng ý") { dialog, which ->

                    // tạo đối tượng OrderDeleteRequest mới để đưa vào api
                    val orderDetail = OrderDeleteRequest(maDonHang, DataSourceUserDangNhap.userId)

                    // call api lấy chi tiết đơn hàng
                    try {
                        Api.retrofitService.deleteOrder(orderDetail)
                            .enqueue(object :
                                Callback<OrderDeleteRespond> {

                                override fun onResponse(
                                    call: Call<OrderDeleteRespond>,
                                    response: Response<OrderDeleteRespond>
                                ) {
                                    val body = response.body()

                                    if (body != null) {
                                        MaterialAlertDialogBuilder(requireContext())
                                            .setTitle("Xóa thành công")
                                            .setMessage(body.status)
                                            .setPositiveButton("Đồng ý") { dialog, which ->
                                                // Respond to positive button press

                                                // điều hướng về lại OrderFragment
                                                val action = OrderDetailFragmentDirections.actionOrderDetailFragmentToOrderFragment()

                                                val navController = Navigation.findNavController(view)

                                                navController.navigate(action)
                                            }
                                            .show()
                                    } else {
                                    }
                                }

                                override fun onFailure(
                                    call: Call<OrderDeleteRespond>,
                                    t: Throwable
                                ) {
                                    Log.d("OrderDetailFragment", "Lỗi mạng!")
                                    t.message?.let { Log.d("OrderDetailFragment", it) }
                                }

                            })

                    } catch (e: Exception) {
                        Log.d("OrderDetailFragment", e.message.toString())
                    }
                }
                .show()
        }

        val danhSachSanPhamVaSoLuongRecyclerView = binding.danhSachSanPhamVaSoLuongRecyclerView
        danhSachSanPhamVaSoLuongRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        cacSanPhamCuaDonHangRecyclerView.adapter = SpecificCategoryBookCardAdapter(requireContext(), )

    }

    override fun onResume() {
        super.onResume()

        // tạo mới đối tượng OrderDetailRequest để đưa vào api
        val orderId = OrderDetailRequest(maDonHang)

        // call api lấy chi tiết đơn hàng
        try {
            Api.retrofitService.getOrderDetail(orderId)
                .enqueue(object :
                    Callback<OrderDetailRespond> {

                    override fun onResponse(
                        call: Call<OrderDetailRespond>,
                        response: Response<OrderDetailRespond>
                    ) {
                        val body = response.body()

                        if (body != null) {
                            Log.d("OrderDetailFragment", body.toString())

                            binding.maDonHangTextView.text = body.orderId

//                            binding.trangThaiGiaoHangTextView.text = body.status
                            when (body.status) {
                                "choxacnhandonhang" -> {
                                    binding.trangThaiGiaoHangTextView.text = "chờ xác nhận đơn hàng"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#afb42b"))
                                    binding.huyDonHangButton.visibility = View.VISIBLE
                                }
                                "daxacnhan" -> {
                                    binding.trangThaiGiaoHangTextView.text = "đơn hàng đã được xác nhận"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#0097a7"))
                                }
                                "danggiao" -> {
                                    binding.trangThaiGiaoHangTextView.text = "đơn hàng đang được giao"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#e65100"))
                                }
                                "dagiao" -> {
                                    binding.trangThaiGiaoHangTextView.text = "giao hàng thành công"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#33691e"))
                                }
                                else -> {
                                    binding.trangThaiGiaoHangTextView.text = "lỗi trạng thái"
                                    binding.trangThaiGiaoHangTextView.setBackgroundResource(R.color.red_600)
                                }
                            }

                            binding.thoiGianDatHangTextView.text = body.date

                            val formattedPrice = NumberFormat.getCurrencyInstance().format(body.total?.toInt())
                            binding.tongTienTextView.text = formattedPrice

                            // danh sách sản phẩm của đơn hàng
                            // gán danh sách sản phẩm của đơn hàng vào datasource
                            DataSourceOrderDetail.products = body.products!!
                            Log.d("OrderDetailFragment", DataSourceOrderDetail.products.toString())

                            val danhSachSanPhamVaSoLuongRecyclerView = binding.danhSachSanPhamVaSoLuongRecyclerView

                            danhSachSanPhamVaSoLuongRecyclerView.layoutManager =
                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                            danhSachSanPhamVaSoLuongRecyclerView.adapter = OrderDetailCardAdapter(requireContext())

                        } else {
                            Log.d("OrderDetailFragment", "body rỗng!")
                        }
                    }

                    override fun onFailure(
                        call: Call<OrderDetailRespond>,
                        t: Throwable
                    ) {
                        Log.d("OrderDetailFragment", "Lỗi mạng!")
                        t.message?.let { Log.d("OrderDetailFragment", it) }
                    }

                })

        } catch (e: Exception) {
            Log.d("OrderDetailFragment", e.message.toString())
        }
    }
}