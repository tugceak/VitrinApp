package com.tugceaksoy.vitrinapp.ui.shops_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.databinding.FragmentShopsDetailBinding
import com.tugceaksoy.vitrinapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopsDetailFragment() :
    BaseFragment<FragmentShopsDetailBinding>(
        FragmentShopsDetailBinding::inflate
    ) {

    private val args: ShopsDetailFragmentArgs by navArgs()
    private  val shopsDetailAdapter: ShopsDetailAdapter  by lazy { ShopsDetailAdapter(
       arrayListOf()
    ) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initBackClick()

    }

    private fun initBackClick() {
       binding.btnBack.setOnClickListener { findNavController().navigate(R.id.backshoptoDiscover) }
    }

    private fun initRecycler() {
        binding.run {
            recyclerViewShops.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewShops.adapter=shopsDetailAdapter
            args.shoparg?.let { shopsDetailAdapter.setNewShopDetailList(it.shop) }
        }


    }
}