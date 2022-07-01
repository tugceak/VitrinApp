package com.tugceaksoy.vitrinapp.ui.shops_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.Shop
import com.tugceaksoy.vitrinapp.data.model.discover.ShopList
import com.tugceaksoy.vitrinapp.databinding.ItemShopDetailBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils


class ShopsDetailAdapter(
    private var newShops: List<Shop>
):RecyclerView.Adapter<ShopsDetailAdapter.MyShopdtlHolder>() {
    class MyShopdtlHolder(val binding:ItemShopDetailBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyShopdtlHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShopDetailBinding.inflate(inflater, parent, false)
        return MyShopdtlHolder(binding)
    }

    override fun onBindViewHolder(holder: MyShopdtlHolder, position: Int) {
        var shop = newShops[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_horizontal_recyclerview)
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
       return newShops.size
    }
    fun setNewShopDetailList(_shopList: List<Shop>) {
        newShops= _shopList
        notifyDataSetChanged()
    }
}