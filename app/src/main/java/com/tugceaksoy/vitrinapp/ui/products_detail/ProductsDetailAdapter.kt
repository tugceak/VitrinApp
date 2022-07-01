package com.tugceaksoy.vitrinapp.ui.products_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.Product
import com.tugceaksoy.vitrinapp.data.model.discover.ProductList
import com.tugceaksoy.vitrinapp.databinding.ItemProductDetailBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils


class ProductsDetailAdapter(
    private var products: List<Product>
):RecyclerView.Adapter<ProductsDetailAdapter.MyProductdtlholder>(){
    class MyProductdtlholder(val binding:ItemProductDetailBinding):RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsDetailAdapter.MyProductdtlholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductDetailBinding.inflate(inflater, parent, false)
        return MyProductdtlholder(binding)
    }

    override fun onBindViewHolder(holder: ProductsDetailAdapter.MyProductdtlholder, position: Int) {
        var product = products[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_horizontal_recyclerview)
        holder.binding.run{
            product.images?.get(0)
                ?.let { it.url?.let { it1 ->
                    GlideUtils.urlToImageView(imgProduct.context,
                        it1,imgProduct)
                } }
            textTitle.text=product.title
            textShop.text=product.shop?.name
            textPrice.text=product.price.toString()

        }
    }

    override fun getItemCount(): Int {
      return products.size
    }
    fun setProductDetailList(_productList: List<Product>) {
        products = _productList
        notifyDataSetChanged()
    }
}