package com.tugceaksoy.vitrinapp.ui.discover

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.component1
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.google.android.material.navigation.NavigationView
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.Output
import com.tugceaksoy.vitrinapp.data.model.discover.*
import com.tugceaksoy.vitrinapp.data.model.discover.Collections
import com.tugceaksoy.vitrinapp.databinding.FragmentDiscoverBinding
import com.tugceaksoy.vitrinapp.ui.HomeActivity
import com.tugceaksoy.vitrinapp.ui.HomeViewModel
import com.tugceaksoy.vitrinapp.ui.base.BaseFragment
import com.tugceaksoy.vitrinapp.ui.discover.adapters.*
import com.tugceaksoy.vitrinapp.utils.GlideUtils
import com.tugceaksoy.vitrinapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>(
    FragmentDiscoverBinding::inflate
) {

    private var listNewShops: List<Shop>? = null
    private var listEditorShops: List<Shop>? = null
    private var listCollections: List<Collections>? = null
    private var listCategory: List<Category>? = null
    private var listProducts: List<Product>? = null
    private val discoverViewModel: DiscoverViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val newShopsAdapter by lazy { NewShopsAdapter(arrayListOf()) }
    private val collectionsAdapter by lazy { CollectionsAdapter(arrayListOf()) }
    private val categoriesAdapter by lazy { CategoriesAdapter(arrayListOf()) }
    private val productsAdapter by lazy { ProductsAdapter(arrayListOf()) }
    private val editorShopsAdapter by lazy { EditorShopsAdapter(arrayListOf()) }
    private var scrollIsIdle: Boolean = false
    private val REQUEST_CODE_SPEECH_INPUT = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        loadData()
        initClick()

    }
    private fun initView() {
        binding.run {
            swipeRefreshLayout.setOnRefreshListener { loadData() }
            recyclerViewProducts.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            recyclerViewCategories.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            recyclerViewCollections.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            recyclerViewEditorShops.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            recyclerViewNewShops.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
        }
    }

    private fun initObserver() {
        discoverViewModel.postDiscover.observe(viewLifecycleOwner, { it ->
            when (it.status) {
                Output.Status.SUCCESS -> {
                    it.data?.let {
                        setNoDataView(it.isEmpty())
                        binding.run {
                            labelNewProducts.text = it[1].title
                            labelCategories.text = it[2].title
                            labelCollections.text = it[3].title
                            labelEditorShops.text = it[4].title
                            labelNewShops.text = it[5].title
                            swipeRefreshLayout.isRefreshing = false
                            emptyLoadingView.isVisible = false
                        }
                        initCarousel(
                            listOf(
                                it[0].featured?.get(0)?.cover?.url,
                                it[0].featured?.get(1)?.cover?.url,
                                it[0].featured?.get(2)?.cover?.url
                            ), listOf(
                                it[0].featured?.get(0)?.title,
                                it[0].featured?.get(1)?.title,
                                it[0].featured?.get(2)?.title
                            )
                        )
                        it[1].products?.let { it1 -> initProducts(it1) }
                        it[2].categories?.let { it1 -> initCategories(it1) }
                        it[3].collections?.let { it1 -> initCollections(it1) }
                        it[4].shops?.let { it1 -> initEditorShops(it1) }
                        it[5].shops?.let { it1 -> initNewShops(it1) }
                    }
                    (activity as HomeActivity).hideLoading()
                }

                Output.Status.ERROR -> {
                    (activity as HomeActivity).hideLoading()
                    setErrorView(it.toString())
                }
                Output.Status.LOADING -> {

                    (activity as HomeActivity).showLoading()
                }
            }
        })
    }

    private fun loadData() {
        lifecycleScope.launch {
            if (Utils.isNetworkAvailable(requireContext())) {
                binding.run {
                    emptyLoadingView.isVisible = true
                    discoverViewModel.getDiscover()
                }
            } else
                setErrorView("Internet bağlantınızı kontrol ediniz.")
        }
    }

    private fun initCarousel(images: List<String?>, text: List<String?>) {
        binding.carouselView.apply {
            size = images.size
            resource = R.layout.carousel_home_item
            autoPlay = true
            indicatorAnimationType = IndicatorAnimationType.THIN_WORM
            carouselOffset = OffsetType.CENTER
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imgCarousel)
                val textView = view.findViewById<TextView>(R.id.textCarousel)
                var imgPath: String? = images[position]
                if (imgPath.isNullOrEmpty())
                    imgPath = ""
                GlideUtils.urlToImageView(requireContext(), imgPath, imageView)
                textView.text = text[position]
            }
            show()
        }
    }

    private fun setErrorView(errorDescription: String) {
        binding.run {
            labelNoData.text = getString(R.string.error_description, errorDescription)
            swipeRefreshLayout.isVisible = false
            noDataView.isVisible = true
        }
    }

    private fun initClick() {
        binding.run {
            btnVoice.setOnClickListener { textToSpeech() }
            btnAllProducts.setOnClickListener {
                listProducts?.let { products ->
                    val actionProduct =
                        DiscoverFragmentDirections.toProductsDetailFragment(ProductList(products))
                    findNavController().navigate(actionProduct)
                }
            }
            btnAllNewShops.setOnClickListener {
                listNewShops?.let { shop ->
                    val actionShop =
                        DiscoverFragmentDirections.toShopsDetailFragment(ShopList(shop))
                    findNavController().navigate(actionShop)
                }
            }
            btnAllCollections.setOnClickListener {
                listCollections?.let { shop ->
                    val actionCollections =
                        DiscoverFragmentDirections.toCollectionDetail(CollectionList(shop))
                    findNavController().navigate(actionCollections)
                }
            }
            btnEditorShops.setOnClickListener {
                listEditorShops?.let { shop ->
                    val actionEditorShops =
                        DiscoverFragmentDirections.toShopsDetailFragment(ShopList(shop))
                    findNavController().navigate(actionEditorShops)
                }
            }
        }
    }

    private fun initNewShops(shops: List<Shop>) {
        binding.run {
            listNewShops = shops
            newShopsAdapter.setNewShopList(shops)
            recyclerViewNewShops.adapter = newShopsAdapter
        }
    }

    private fun initCategories(categories: List<Category>) {
        binding.run {
            listCategory = categories
            categoriesAdapter.setCategoryList(categories)
            recyclerViewCategories.adapter = categoriesAdapter
        }
    }

    private fun initCollections(collections: List<Collections>) {
        binding.run {
            listCollections = collections
            collectionsAdapter.setCollectionList(collections)
            recyclerViewCollections.adapter = collectionsAdapter
        }
    }

    private fun initProducts(products: List<Product>) {
        binding.run {
            listProducts = products
            homeViewModel.setProductList(products)
            productsAdapter.setProductList(products)
            recyclerViewProducts.adapter = productsAdapter
        }
    }

    private fun initEditorShops(shops: List<Shop>) {
        binding.run {
            listEditorShops = shops
            editorShopsAdapter.setEditorShopList(shops)
            recyclerViewEditorShops.adapter = editorShopsAdapter
            setGrayscaleColorFilter()

            val snapHelper = GravitySnapHelper(Gravity.START)
            snapHelper.attachToRecyclerView(recyclerViewEditorShops)
            shops[0].cover?.let {
                it.url?.let { it1 ->
                    GlideUtils.urlToImageView(
                        requireContext(),
                        it1, imgEditorBackground
                    )
                }
            }
            recyclerViewEditorShops.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (scrollIsIdle) {
                        recyclerViewEditorShops.layoutManager?.let {
                            val snapView = snapHelper.findSnapView(it)
                            val snapPosition = snapView?.let { snap -> it.getPosition(snap) }
                            snapPosition?.let { position ->
                                shops[position].cover?.let { cover ->
                                    cover?.url?.let { it1 ->
                                        GlideUtils.urlToImageView(
                                            requireContext(),
                                            it1,
                                            imgEditorBackground
                                        ) } } } } } }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    scrollIsIdle = newState != RecyclerView.SCROLL_STATE_DRAGGING
                }
            })
        } }

    private fun setGrayscaleColorFilter() {
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)
        binding.imgEditorBackground.colorFilter = filter
    }

    private fun setNoDataView(listIsEmpty: Boolean) {
        binding.run {
            labelNoData.text = getString(R.string.no_data_description)
            swipeRefreshLayout.isVisible = !listIsEmpty
            noDataView.isVisible = listIsEmpty
        }
    }

    private fun textToSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        } catch (e: Exception) {
            Utils.showToast(requireContext(), " " + e.message)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                binding.editSearch.setText(
                    Objects.requireNonNull(res)[0]
                )
            }
        }
    }


}