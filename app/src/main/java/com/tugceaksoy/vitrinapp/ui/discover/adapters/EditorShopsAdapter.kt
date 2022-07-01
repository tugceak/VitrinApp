package com.tugceaksoy.vitrinapp.ui.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.Shop
import com.tugceaksoy.vitrinapp.data.model.discover.ShopList
import com.tugceaksoy.vitrinapp.databinding.ItemEditorShopsBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils


class EditorShopsAdapter(
    private var editorShopsList: List<Shop>
) : RecyclerView.Adapter<EditorShopsAdapter.MyEditorShopsHolder>() {

    class MyEditorShopsHolder(val binding: ItemEditorShopsBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorShopsAdapter.MyEditorShopsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEditorShopsBinding.inflate(inflater, parent, false)
        return MyEditorShopsHolder(binding)
    }

    override fun onBindViewHolder(holder:MyEditorShopsHolder, position: Int) {
        var editorShop = editorShopsList[position]
        holder.itemView.animation=AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_horizontal_recyclerview)
        holder.binding.run {
            textTitle.text=editorShop.name
            textShop.text=editorShop.definition
            editorShop.popular_products?.get(0)?.images?.get(0)?.url?.let {
                GlideUtils.urlToImageView(imgShop1.context,
                    it,imgShop1)
            }
            editorShop.popular_products?.get(1)?.images?.get(0)?.url?.let {
                GlideUtils.urlToImageView(imgShop2.context,
                    it,imgShop2)
            }
            editorShop.popular_products?.get(2)?.images?.get(0)?.url?.let {
                GlideUtils.urlToImageView(imgShop3.context,
                    it,imgShop3)
            }
            editorShop.logo?.let { cover->
                cover.url?.let { GlideUtils.urlToImageView(imgLogo.context, it,imgLogo) }
            }
        }

    }

    override fun getItemCount(): Int {
        return editorShopsList.size
    }

    fun setEditorShopList(_editorShopsList: List<Shop>) {
        editorShopsList = _editorShopsList
        notifyDataSetChanged()
    }


}
