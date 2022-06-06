package com.CapstoneProject.wicara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityDetailArtikel1Binding
import com.CapstoneProject.wicara.network.ArtikelResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailArtikel1Activity : AppCompatActivity() {
    private var binding : ActivityDetailArtikel1Binding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtikel1Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        val data = intent.getParcelableExtra<ArtikelResponseItem>(EXTRA_DATA) as ArtikelResponseItem

        binding!!.textView17?.text = data.title
        binding!!.textView25?.text = data.title

        Glide.with(this)
            .load(data.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .into(binding!!.imageView8)


        binding!!.textView31.text = data.description

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}