package com.tugceaksoy.vitrinapp.ui.collection_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.databinding.FragmentCollectionsDetailBinding
import com.tugceaksoy.vitrinapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionsDetailFragment() : BaseFragment<FragmentCollectionsDetailBinding>(
    FragmentCollectionsDetailBinding::inflate
)  {
    private val args:CollectionsDetailFragmentArgs by navArgs()
    private val collectionsDetailAdapter: CollectionsDetailAdapter by lazy { CollectionsDetailAdapter(arrayListOf()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initBackClick()

    }
    private fun initBackClick(){
      binding.btnBack.setOnClickListener { findNavController().navigate(R.id.backcollectionToDiscover) }

    }
    private fun initRecycler(){
        binding.run {
            recyclerViewCollections.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewCollections.adapter=collectionsDetailAdapter
            args.collection?.let { collectionsDetailAdapter.setCollectionDetailList(it.collection) }

        }
    }
    }
