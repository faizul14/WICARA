package com.CapstoneProject.wicara.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.FragmentSettingBinding
import com.CapstoneProject.wicara.databinding.FragmentUserBinding

class UserFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentUserBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user, container, false)
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root : View = binding.root

        binding.cdAbout.setOnClickListener(this)

        return root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.cd_about -> {
                val url = "https://wicara2022.github.io/"
                val move = Intent(Intent.ACTION_VIEW)
                move.setData(Uri.parse(url))
                startActivity(move)
            }
        }
    }

}