package com.tugceaksoy.vitrinapp.ui.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.Shop
import com.tugceaksoy.vitrinapp.data.model.discover.ShopList
import com.tugceaksoy.vitrinapp.databinding.ItemNewShopsBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils

class NewShopsAdapter(
    private var shoptList: List<Shop>
) :RecyclerView.Adapter<NewShopsAdapter.MyHolder>(){

    class MyHolder(val binding: ItemNewShopsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewShopsAdapter.MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewShopsBinding.inflate(inflater, parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: NewShopsAdapter.MyHolder, position: Int) {
        var shop = shoptList[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context,R.anim.anim_horizontal_recyclerview)
        holder.binding.run {
            textTitle.text=shop.name
            textDescription.text=shop.definition
            textProductCount.text=shop.product_count.toString()
            shop.cover?.let {
                it.url?.let { it1 ->
                    GlideUtils.urlToImageView(
                        imgShop.context,
                        it1,
                        imgShop
                    )
                }
            }
            shop.logo?.let {
                it.url?.let { it1 ->
                    GlideUtils.urlToImageView(
                        imgLogo.context,
                        it1,
                        imgLogo
                    )
                }
            }
        }


    }

    override fun getItemCount(): Int {
      return shoptList.size
    }

    fun setNewShopList(_shopList: List<Shop>) {
        shoptList = _shopList
        notifyDataSetChanged()
    }

}
