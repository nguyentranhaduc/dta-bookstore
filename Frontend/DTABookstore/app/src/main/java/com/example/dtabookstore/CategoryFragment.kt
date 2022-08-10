package com.example.dtabookstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dtabookstore.adapter.CategoryAdapter
import com.example.dtabookstore.databinding.FragmentCategoryBinding
import com.example.dtabookstore.databinding.FragmentShoppingCartBinding

class CategoryFragment : Fragment() {

    // View binding
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val theLoaiRecyclerView = binding.theLoaiRecyclerView

//        theLoaiRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        theLoaiRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        theLoaiRecyclerView.adapter = CategoryAdapter(requireContext())
    }

    // reset view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}