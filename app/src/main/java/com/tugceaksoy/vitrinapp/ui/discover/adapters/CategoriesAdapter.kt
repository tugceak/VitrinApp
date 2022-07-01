package com.tugceaksoy.vitrinapp.ui.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.Category
import com.tugceaksoy.vitrinapp.databinding.ItemCategoriesBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils

class CategoriesAdapter(
    private var categoryList: List<Category>
):RecyclerView.Adapter<CategoriesAdapter.MyCategoryHolder>() {
    class MyCategoryHolder(val binding:ItemCategoriesBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.MyCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return MyCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.MyCategoryHolder, position: Int) {
        var category = categoryList[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_horizontal_recyclerview)
        holder.binding.run {
            category.cover?.url?.let {
                GlideUtils.urlToImageView(imgCategory.context,
                    it,imgCategory)
            }
            textCategory.text=category.name
        }
    }

    override fun getItemCount(): Int {
      return categoryList.size
    }
    fun setCategoryList(_categoryList: List<Category>) {
        categoryList = _categoryList
        notifyDataSetChanged()
    }

}
