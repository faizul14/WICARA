package com.CapstoneProject.wicara.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.CapstoneProject.wicara.databinding.CardVidioBinding
import com.CapstoneProject.wicara.network.VidioResponseItem
import com.bumptech.glide.Glide

class VidioAdapter : RecyclerView.Adapter<VidioAdapter.ListViewHolder>() {
    private val listVidio = ArrayList<VidioResponseItem>()
    fun seData(data : List<VidioResponseItem>){
        listVidio.addAll(data)
    }
    class ListViewHolder(private val binding: CardVidioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : VidioResponseItem){
            binding.itemVidio.setOnClickListener {

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VidioAdapter.ListViewHolder {
        val binding = CardVidioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VidioAdapter.ListViewHolder, position: Int) {
        holder.bind(listVidio[position])
    }

    override fun getItemCount(): Int = listVidio.size
}