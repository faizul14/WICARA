package com.CapstoneProject.wicara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityVidioBinding
import com.CapstoneProject.wicara.viewmodel.VidioViewModel

class VidioActivity : AppCompatActivity() {
    private var binding : ActivityVidioBinding? = null
    private lateinit var adapter: VidioAdapter
    private lateinit var viewModel : VidioViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVidioBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //for adapter
        adapter = VidioAdapter()
        binding?.rvVidio?.layoutManager = LinearLayoutManager(this)
        binding?.rvVidio?.setHasFixedSize(true)

        //for viewmodel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[VidioViewModel::class.java]
//        viewModel.getVidio()

        viewModel.dataVidio.observe(this, {data->
            if (data != null){
                binding?.rvVidio?.adapter = adapter
                adapter.seData(data)
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}