package com.tugceaksoy.vitrinapp.ui.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.Product
import com.tugceaksoy.vitrinapp.data.model.discover.ProductList
import com.tugceaksoy.vitrinapp.databinding.ItemProductsBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils

class ProductsAdapter(
    private var productList:  List<Product>
):RecyclerView.Adapter<ProductsAdapter.MyProductHolder>(){

    class MyProductHolder(val binding: ItemProductsBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.MyProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductsBinding.inflate(inflater, parent, false)
        return MyProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.MyProductHolder, position: Int) {
        var product = productList[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context,R.anim.anim_horizontal_recyclerview)
        holder.binding.run{
            product.images[0]?.let {
                it.url?.let { it1 ->
                    GlideUtils.urlToImageView(imgProduct.context,
                        it1,imgProduct)
                }
            }
            textTitle.text=product.title
            textShop.text=product.shop?.name
            textPrice.text=product.price.toString()

        }
    }

    override fun getItemCount(): Int {
       return productList.size
    }
    fun setProductList(_productList:List<Product>) {
        productList = _productList
        notifyDataSetChanged()
    }

}
