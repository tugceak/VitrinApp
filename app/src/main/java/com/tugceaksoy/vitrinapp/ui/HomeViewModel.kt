package com.tugceaksoy.vitrinapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugceaksoy.vitrinapp.data.model.Output
import com.tugceaksoy.vitrinapp.data.model.discover.Collections
import com.tugceaksoy.vitrinapp.data.model.discover.DiscoverModel
import com.tugceaksoy.vitrinapp.data.model.discover.Product
import com.tugceaksoy.vitrinapp.data.model.discover.Shop

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var postListProduct : List<Product>? = null
    var postListCollection : List<Collections>? = null
    var postListShops:List<Shop>? = null

    fun setProductList(list: List<Product>) {
        postListProduct=list
    }
    fun getProductList() :List<Product>? {
        return postListProduct
    }
    fun setCollectionList(list: List<Collections>) {
        postListCollection=list
    }
    fun getCollectionList() :List<Collections>? {
        return postListCollection
    }
    fun setShopList(list: List<Shop>) {
        postListShops=list
    }
    fun getShopList() :List<Shop>? {
        return postListShops
    }

}