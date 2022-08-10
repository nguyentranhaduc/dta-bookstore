package com.example.dtabookstore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.dtabookstore.activity.MainActivity
import com.example.dtabookstore.activity.SignInActivity
import com.example.dtabookstore.data.DataSourceGioHang
import com.example.dtabookstore.data.DataSourceOrder
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.databinding.FragmentProfileBinding
import com.example.dtabookstore.network.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProfileFragment : Fragment() {

    // View binding
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val actionProfileToShoppingCart =
            ProfileFragmentDirections.actionProfileFragmentToShoppingCartFragment()
        val actionProfileToOrder = ProfileFragmentDirections.actionProfileFragmentToOrderFragment()

//        binding.capNhatButton.setOnClickListener {
//            MaterialAlertDialogBuilder(requireContext())
//                .setTitle(resources.getString(R.string.title))
//                .setMessage(resources.getString(R.string.supporting_text))
//                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
//                    // Respond to neutral button press
//                }
//                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
//                    // Respond to negative button press
//                }
//                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
//                    // Respond to positive button press
//                }
//                .show()
//        }

        binding.capNhatButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.profile_xac_nhan_title))
                .setMessage(resources.getString(R.string.profile_xac_nhan_message))
                .setNegativeButton(resources.getString(R.string.profile_huy_bo)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.profile_dong_y)) { dialog, which ->
                    // Respond to positive button press
                }
                .show()
        }

        binding.xemLichSuMuaHangButton.setOnClickListener {
            this.findNavController().navigate(actionProfileToOrder)
        }


        //click vao top app bar
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.shoppingCartFragment -> {
                    // Handle favorite icon press
                    this.findNavController().navigate(actionProfileToShoppingCart)
                    true
                }
//                R.id.more -> {
//                    // Handle more item (inside overflow menu) press
//                    true
//                }
                else -> false
            }
        }

        binding.capNhatButton.setOnClickListener {
            // userId
            val userId = DataSourceUserDangNhap.userId
            // lấy các thông tin từ editText
            val fullName = binding.fullNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val address = binding.addressEditText.text.toString()
            // tạo đối tượng UserUpdateInformation với các trường dữ liệu lấy được
            val userUpdateInformation =
                UserUpdateInformation(userId, fullName, phone, email, address)

            try {
                Api.retrofitService.postUpdateUserInformation(userUpdateInformation)
                    .enqueue(object :
                        Callback<UserUpdateInformationRespond> {
                        override fun onResponse(
                            call: Call<UserUpdateInformationRespond>,
                            response: Response<UserUpdateInformationRespond>
                        ) {
                            val body = response.body()

                            Log.d("ProfileFragment", body.toString())

                            if (body != null) {

                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("Thành công")
                                    .setMessage(body.status)
                                    .setPositiveButton("Đồng ý") { dialog, which ->
                                        val action =
                                            ProfileFragmentDirections.actionProfileFragmentSelf(
                                                DataSourceUserDangNhap.userId
                                            )

                                        val navController = Navigation.findNavController(view)

                                        navController.navigate(action)
                                    }
                                    .show()

                            } else {
                                Log.d("ProfileFragment", "body null")
                            }
                        }

                        override fun onFailure(
                            call: Call<UserUpdateInformationRespond>,
                            t: Throwable
                        ) {
                            Snackbar.make(binding.capNhatButton, "Lỗi mạng!", Snackbar.LENGTH_LONG)
                                .show()
                            Log.d("ProfileFragment", "Lỗi mạng!")
                            t.message?.let { Log.d("ProfileFragment", it) }
                        }

                    })

            } catch (e: Exception) {
                Log.d("ProfileFragment", e.message.toString())
            }
        }

        binding.dangXuatButton.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Đăng xuất")
                .setMessage("Bạn có chắc muốn đăng xuất?")
                .setNegativeButton("Hủy bỏ") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("Đăng xuất") { dialog, which ->
                    // Respond to positive button press

                    // xóa dữ liệu user đã đăng nhập và các datasource khác
                    DataSourceGioHang.books.clear()
                    DataSourceGioHang.tongTien = 0

                    DataSourceOrder.orders.clear()



                    // chuyển hướng sang màn hình đăng nhập
                    val intent = Intent(getActivity(), SignInActivity::class.java)
                    getActivity()?.startActivity(intent)

                }
                .show()
        }

    }

    override fun onResume() {
        super.onResume()

        // call api lấy thông tin User đã đăng nhập
        val user = UserProfile(DataSourceUserDangNhap.userId)

        try {
            Api.retrofitService.getUserInformation(user).enqueue(object :
                Callback<UserProfileRespond> {
                override fun onResponse(
                    call: Call<UserProfileRespond>,
                    response: Response<UserProfileRespond>
                ) {
                    val body = response.body()

                    Log.d("ProfileFragment", body.toString())

                    if (body != null) {
                        Log.d("ProfileFragment", "body có dữ liệu")

                        binding.userNameTextView.text = body.fullName
                        binding.userMailTextView.text = body.email

                        binding.fullNameEditText.setText(body.fullName)
                        binding.phoneEditText.setText(body.phone)
                        binding.emailEditText.setText(body.email)
                        binding.addressEditText.setText(body.address)

                    } else {
                        Log.d("ProfileFragment", "body null")
                    }
                }

                override fun onFailure(call: Call<UserProfileRespond>, t: Throwable) {
                    Snackbar.make(binding.capNhatButton, "Lỗi mạng!", Snackbar.LENGTH_LONG).show()
                    Log.d("ProfileFragment", "Lỗi mạng!")
                    t.message?.let { Log.d("ProfileFragment", it) }
                }

            })

        } catch (e: Exception) {
            Log.d("ProfileFragment", e.message.toString())
        }
    }
}