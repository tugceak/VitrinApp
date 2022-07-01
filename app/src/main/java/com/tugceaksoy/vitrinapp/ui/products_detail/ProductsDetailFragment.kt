package com.tugceaksoy.vitrinapp.ui.products_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.databinding.FragmentProductsDetailBinding
import com.tugceaksoy.vitrinapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsDetailFragment() : BaseFragment<FragmentProductsDetailBinding>(
    FragmentProductsDetailBinding::inflate
) {
    private val args: ProductsDetailFragmentArgs by navArgs()
    private val productsDetailAdapter: ProductsDetailAdapter  by lazy { ProductsDetailAdapter(arrayListOf()
    ) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initBackButton()
    }

    private fun initBackButton() {
        binding.btnBack.setOnClickListener { findNavController().navigate(R.id.backproductToDiscover) }
    }

    private fun initRecycler() {
        binding.run {
            recyclerViewProducts.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewProducts.adapter=productsDetailAdapter
            args.products?.let { productsDetailAdapter.setProductDetailList(it.products) }


        }
    }

}
