package com.CapstoneProject.wicara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityArtikelBinding
import com.CapstoneProject.wicara.viewmodel.ArtikelViewModel

class ArtikelActivity : AppCompatActivity() {
    private var binding: ActivityArtikelBinding? = null
    private lateinit var adapter: ArtikelAdapter
    private lateinit var viewModel : ArtikelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        adapter = ArtikelAdapter()
        binding!!.rvArtikel.layoutManager = LinearLayoutManager(this)
        binding!!.rvArtikel.setHasFixedSize(true)


        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ArtikelViewModel::class.java]
        viewModel.getDataArtikel()

        setData()

    }

    private fun setData(){
        viewModel.dataArtikel.observe(this, {data->
            if (data!= null){
                binding!!.rvArtikel.adapter = adapter
                adapter.setArtikel(data)
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        binding =  null
    }
}