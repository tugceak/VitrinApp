package com.tugceaksoy.vitrinapp.ui.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.CollectionList
import com.tugceaksoy.vitrinapp.data.model.discover.Collections
import com.tugceaksoy.vitrinapp.databinding.ItemCollectionsBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils


class CollectionsAdapter(
    private var collectionList: List<Collections>
):RecyclerView.Adapter<CollectionsAdapter.MyCollectionHolder>(){
    class MyCollectionHolder(val binding: ItemCollectionsBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyCollectionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionsBinding.inflate(inflater, parent, false)
        return CollectionsAdapter.MyCollectionHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsAdapter.MyCollectionHolder, position: Int) {
        var collection = collectionList[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_horizontal_recyclerview)
        holder.binding.run {
            collection.cover?.url?.let {
                GlideUtils.urlToImageView(imgCollection.context,
                    it,imgCollection)
            }
            textTitle.text=collection.title
            textDefinition.text=collection.definition
        }
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }
    fun setCollectionList(_collectionList: List<Collections>) {
        collectionList = _collectionList
        notifyDataSetChanged()
    }

}
