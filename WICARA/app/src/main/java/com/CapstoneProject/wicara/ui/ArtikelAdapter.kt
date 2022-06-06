package com.CapstoneProject.wicara.ui

import android.animation.Animator
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.CardArtikelBinding
import com.CapstoneProject.wicara.network.ArtikelResponseItem
import com.CapstoneProject.wicara.network.DataItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ArtikelAdapter : RecyclerView.Adapter<ArtikelAdapter.ListViewHolder>() {
    private val listArtikel = ArrayList<ArtikelResponseItem>()
    fun setArtikel(data : List<ArtikelResponseItem>){
        listArtikel.clear()
        listArtikel.addAll(data)
    }
    class ListViewHolder(private val binding: CardArtikelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (data : ArtikelResponseItem){
            Glide.with(itemView.context)
                .load(data.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgArtikel)
            binding.txtTittle.text = data.title
            binding.txtDescription.text = data.description
            binding.itemArtikel.setOnClickListener {
//                val list =
                val list = ArtikelResponseItem(
                    data.image,
                    "",
                    data.description,
                    data.id,
                    data.title,
                    ""
                    )

                val move = Intent(itemView.context, DetailArtikel1Activity::class.java)
                move.putExtra(DetailArtikel1Activity.EXTRA_DATA, list)
                itemView.context.startActivity(move)
            }


//            Glide.with(itemView.context)
//                .load(data.avatar)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(binding.imgArtikel)
//            binding.txtTittle.text = data.firstName
//            binding.txtDescription.text = data.lastName

//            binding.itemArtikel.setOnClickListener {
//                Toast.makeText(itemView.context, "Di klik", Toast.LENGTH_SHORT).show()
//            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtikelAdapter.ListViewHolder {
        val binding = CardArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtikelAdapter.ListViewHolder, position: Int) {
        holder.bind(listArtikel[position])
    }

    override fun getItemCount(): Int = listArtikel.size
}