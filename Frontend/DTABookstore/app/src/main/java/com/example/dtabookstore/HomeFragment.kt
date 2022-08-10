package com.example.dtabookstore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dtabookstore.adapter.BookCardAdapter
import com.example.dtabookstore.adapter.BookCardAdapter3
import com.example.dtabookstore.data.DataSourceBook
import com.example.dtabookstore.data.DataSourceBooks
import com.example.dtabookstore.databinding.FragmentHomeBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.Book
import com.example.dtabookstore.network.UserDangNhapRespond
import com.example.dtabookstore.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class HomeFragment : Fragment() {

    // View binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    // Hàm onCreate
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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Carousel
        val imageList = ArrayList<SlideModel>() // Create image list
        //Them hinh
        imageList.add(SlideModel(R.drawable.carousel_1, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.carousel_2, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.carousel_3, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.carousel_5, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.carousel_4, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.carousel_6, ScaleTypes.CENTER_CROP))
        //Thêm list hình vào carousel
        binding.slider.setImageList(imageList)


        // Khai báo các biến tham chiếu action trong nav graph
        val actionHomeToShoppingCart = HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment()


        //click vao top app bar
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {

                R.id.shopping_cart -> {
                    // Handle favorite icon press
                    this.findNavController().navigate(actionHomeToShoppingCart)
                    true
                }

                else -> false
            }
        }

        try {
            Api.retrofitService.getDanhSachSach().enqueue(object :
                Callback<MutableList<Book>> {

                override fun onResponse(
                    call: Call<MutableList<Book>>,
                    response: Response<MutableList<Book>>
                ) {

                    val body = response.body()

                    if (body != null) {
                        DataSourceBooks.books = body
                        Log.d("MainActivityBody", DataSourceBooks.books.toString())
                    } else {
                        Log.d("MainActivity", "body null")
                    }
                }

                override fun onFailure(call: Call<MutableList<Book>>, t: Throwable) {
                    Snackbar.make(binding.root, "Lỗi mạng!", Snackbar.LENGTH_LONG).show()
                }

            })

        } catch (e: Exception) {
            Log.d("HomeFragment", e.message.toString())
        }

    }

    override fun onResume() {
        super.onResume()

        // - Tạo các biến tham chiếu tham chiếu đến RecyclerView trong layout
        val sachBanChayNhatRecyclerView = binding.sachBanChayNhatRecyclerView
        val sachMoiNhatRecyclerView = binding.sachMoiNhatRecyclerView

        // Gán LayoutManager
        sachBanChayNhatRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        sachMoiNhatRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        //-------------------------- GÁN DANH SÁCH SÁCH CHO 2 CÁI RECYCLERVIEW ----------------------------

        // - Để nói với recyclerView dùng class BookCardAdapter, tạo mới một thể hiện BookAdapter, nó mong
        // chờ 2 tham số:
        //      1. context (this)
        //      2. Các Book trong myDataSet
        // - Gán adapter thông qua thuộc tính "adapter" của recyclerView

        // RecyclerView sách bán chạy
        sachBanChayNhatRecyclerView.adapter =
            BookCardAdapter3(requireContext(), DataSourceBooks.findBookBanChay().shuffled())

        // RecyclerView sách mới
        sachMoiNhatRecyclerView.adapter =
            BookCardAdapter3(requireContext(), DataSourceBooks.findBookMoiNhat().shuffled())
        //--------------------------------------------------------------------------------------------------
    }

    // reset view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}