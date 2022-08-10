package com.example.dtabookstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.BookCardAdapter
import com.example.dtabookstore.adapter.SpecificCategoryBookCardAdapter
import com.example.dtabookstore.data.DataSourceBook
import com.example.dtabookstore.data.DataSourceBooks
import com.example.dtabookstore.databinding.FragmentBookDetailBinding
import com.example.dtabookstore.databinding.FragmentSpecificCategoryBinding

class SpecificCategoryFragment : Fragment() {

    // View binding
    private var _binding: FragmentSpecificCategoryBinding? = null
    private val binding get() = _binding!!



    companion object{
        // action args
        val SPECIFIC_CATEGORY = "specific_category"
    }



    // Khởi tạo các biến chứa giá trị các đối số truyền vào, sử dụng trong fragment này
    private lateinit var specificCategory: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // lấy các đối số truyền vào destination này
        arguments?.let {
            specificCategory = it.getString(SPECIFIC_CATEGORY).toString()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpecificCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val theLoai: String = specificCategory

        when (theLoai) {
            "sachthieunhi" -> {
                binding.topAppBar.title = "Sách thiếu nhi"
            }
            "sachvanhoc" -> {
                binding.topAppBar.title = "Sách văn học"
            }
            "sachkynangsong" -> {
                binding.topAppBar.title = "Sách kỹ năng sống"
            }
            "sachkinhte" -> {
                binding.topAppBar.title = "Sách kinh tế"
            }
            "sachthamkhao" -> {
                binding.topAppBar.title = "Sách tham khảo"
            }
            "sachhocngoaingu" -> {
                binding.topAppBar.title = "Sách học ngoại ngữ"
            }
        }

        val theLoaiCuTheRecyclerView = binding.theLoaiCuTheRecyclerView

        theLoaiCuTheRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        theLoaiCuTheRecyclerView.adapter = SpecificCategoryBookCardAdapter(requireContext(), DataSourceBooks.findSpecificCategoryBooks(specificCategory).shuffled())
    }

}