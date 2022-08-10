package com.example.dtabookstore

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.Navigation
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dtabookstore.data.DataSourceBook
import com.example.dtabookstore.data.DataSourceBooks
import com.example.dtabookstore.data.DataSourceGioHang
import com.example.dtabookstore.databinding.FragmentBookDetailBinding
import com.example.dtabookstore.network.Book
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.NumberFormat
import javax.sql.DataSource

class BookDetailFragment : Fragment() {

    // View binding
    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!



    companion object{
        // action args
        val BOOK_ID = "book_id"
    }


    // Khởi tạo các biến chứa giá trị các đối số truyền vào, sử dụng trong fragment này
    private lateinit var bookId: String



    // override method onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // lấy các đối số truyền vào destination này
        arguments?.let {
            bookId = it.getString(BOOK_ID).toString()
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // lấy Book được truyền vào qua bookId
        val theBook: Book? = DataSourceBooks.findABook(bookId)
        // Gán các giá trị của Book truyền vào vào các View trong trang detail
        binding.bookTitleTextView.text = theBook?.title // Gán tên sách

        val formattedPrice = NumberFormat.getCurrencyInstance().format(theBook?.price) // Gán giá sách
        binding.bookPriceTextView.text = formattedPrice // Gán giá sách

        // Gán các chi tiết sản phẩm
        binding.nhaXuatBanTextView.text = theBook?.detail?.get("nhaxuatban")
        binding.ngayXuatBanTextView.text = theBook?.detail?.get("ngayxuatban")
        binding.soTrangTextView.text = theBook?.detail?.get("sotrang")
        binding.congTyPhatHanhTextView.text = theBook?.detail?.get("congtyphathanh")



        //Carousel
        val imageList = ArrayList<SlideModel>() // Create image list
        //Them hinh
//        imageList.add(SlideModel(R.drawable.book_taichinhcanhan, ScaleTypes.CENTER_INSIDE))

        theBook?.imgSrc?.forEach { imageList.add(SlideModel(it, ScaleTypes.CENTER_INSIDE)) }
        //Thêm list hình vào carousel
        binding.sliderBookDetail.setImageList(imageList)


        binding.moTaSanPhamTextView.text = theBook?.description // Gán mô tả sản phẩm




        // Gán sự kiện nhấp Enter bỏ focus ở số lượng edit text
        binding.soLuongEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }


        // Gán sự kiện nhấn nút thêm vào giỏ hàng
        binding.themVaoGioHangButton.setOnClickListener {
            val soLuong = binding.soLuongEditText.text.toString()

            if (soLuong != "") {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getText(R.string.detail_xac_nhan_title))
                    .setMessage(
                        getString(
                            R.string.detail_xac_nhan_message,
                            binding.bookTitleTextView.text,
                            soLuong
                        )
                    )
                    .setNegativeButton(resources.getString(R.string.detail_huy_bo)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.detail_dong_y)) { dialog, which ->
                        // Respond to positive button press

                        // nếu sách đã tồn tại trong giỏ hàng thì tiến hành cập nhật
                        if (DataSourceGioHang.findABook(bookId)) {
                            DataSourceGioHang.updateQuantityOfABook(bookId, soLuong.toInt())
                        } else {
                            DataSourceGioHang.books.add(
                                mutableMapOf(
                                    "productId" to bookId,
                                    "quantity" to soLuong
                                )
                            )
                            // cập nhật số tổng tiền sau khi thêm sản phẩm vào giỏ hàng
                            val theBook = DataSourceBooks.findABook(bookId)
                            DataSourceGioHang.tongTien += ( theBook!!.price * soLuong.toInt() )
                        }


                        val action = BookDetailFragmentDirections.actionBookDetailFragmentToShoppingCartFragment()

                        val navController = Navigation.findNavController(view)

                        navController.navigate(action)
                    }
                    .show()
            }
        }
    }



    // reset view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}