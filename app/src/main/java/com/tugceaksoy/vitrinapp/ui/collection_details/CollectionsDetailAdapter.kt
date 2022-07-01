package com.tugceaksoy.vitrinapp.ui.collection_details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.CollectionList
import com.tugceaksoy.vitrinapp.data.model.discover.Collections
import com.tugceaksoy.vitrinapp.databinding.ItemCollectionDetailBinding
import com.tugceaksoy.vitrinapp.utils.GlideUtils

class CollectionsDetailAdapter(
    private var collections: List<Collections>
):RecyclerView.Adapter<CollectionsDetailAdapter.MyCollectionHolder> (){

    class MyCollectionHolder(val binding:ItemCollectionDetailBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCollectionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionDetailBinding.inflate(inflater, parent, false)
        return MyCollectionHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCollectionHolder, position: Int) {
        var collection = collections[position]
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
        return collections.size
    }
    fun setCollectionDetailList(_collectionList: List<Collections>) {
        collections = _collectionList
        notifyDataSetChanged()
    }
}