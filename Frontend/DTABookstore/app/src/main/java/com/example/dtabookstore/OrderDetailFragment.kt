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
        binding.topAppBar.title = "????n h??ng " + maDonHang

        // set s??? ki???n click cho n??t h???y ????n h??ng
        binding.huyDonHangButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Th??ng b??o")
                .setMessage("B???n c?? ch???c ch???n mu???n h???y ????n h??ng n??y?")
                .setNegativeButton("H???y b???") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("?????ng ??") { dialog, which ->

                    // t???o ?????i t?????ng OrderDeleteRequest m???i ????? ????a v??o api
                    val orderDetail = OrderDeleteRequest(maDonHang, DataSourceUserDangNhap.userId)

                    // call api l???y chi ti???t ????n h??ng
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
                                            .setTitle("X??a th??nh c??ng")
                                            .setMessage(body.status)
                                            .setPositiveButton("?????ng ??") { dialog, which ->
                                                // Respond to positive button press

                                                // ??i???u h?????ng v??? l???i OrderFragment
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
                                    Log.d("OrderDetailFragment", "L???i m???ng!")
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

        // t???o m???i ?????i t?????ng OrderDetailRequest ????? ????a v??o api
        val orderId = OrderDetailRequest(maDonHang)

        // call api l???y chi ti???t ????n h??ng
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
                                    binding.trangThaiGiaoHangTextView.text = "ch??? x??c nh???n ????n h??ng"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#afb42b"))
                                    binding.huyDonHangButton.visibility = View.VISIBLE
                                }
                                "daxacnhan" -> {
                                    binding.trangThaiGiaoHangTextView.text = "????n h??ng ???? ???????c x??c nh???n"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#0097a7"))
                                }
                                "danggiao" -> {
                                    binding.trangThaiGiaoHangTextView.text = "????n h??ng ??ang ???????c giao"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#e65100"))
                                }
                                "dagiao" -> {
                                    binding.trangThaiGiaoHangTextView.text = "giao h??ng th??nh c??ng"
                                    binding.trangThaiGiaoHangTextView.setTextColor(Color.parseColor("#33691e"))
                                }
                                else -> {
                                    binding.trangThaiGiaoHangTextView.text = "l???i tr???ng th??i"
                                    binding.trangThaiGiaoHangTextView.setBackgroundResource(R.color.red_600)
                                }
                            }

                            binding.thoiGianDatHangTextView.text = body.date

                            val formattedPrice = NumberFormat.getCurrencyInstance().format(body.total?.toInt())
                            binding.tongTienTextView.text = formattedPrice

                            // danh s??ch s???n ph???m c???a ????n h??ng
                            // g??n danh s??ch s???n ph???m c???a ????n h??ng v??o datasource
                            DataSourceOrderDetail.products = body.products!!
                            Log.d("OrderDetailFragment", DataSourceOrderDetail.products.toString())

                            val danhSachSanPhamVaSoLuongRecyclerView = binding.danhSachSanPhamVaSoLuongRecyclerView

                            danhSachSanPhamVaSoLuongRecyclerView.layoutManager =
                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                            danhSachSanPhamVaSoLuongRecyclerView.adapter = OrderDetailCardAdapter(requireContext())

                        } else {
                            Log.d("OrderDetailFragment", "body r???ng!")
                        }
                    }

                    override fun onFailure(
                        call: Call<OrderDetailRespond>,
                        t: Throwable
                    ) {
                        Log.d("OrderDetailFragment", "L???i m???ng!")
                        t.message?.let { Log.d("OrderDetailFragment", it) }
                    }

                })

        } catch (e: Exception) {
            Log.d("OrderDetailFragment", e.message.toString())
        }
    }
}