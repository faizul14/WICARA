package com.CapstoneProject.wicara.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding : FragmentSettingBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_setting, container, false)

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root : View = binding.root

        binding.btnSwitch.setOnCheckedChangeListener{_:CompoundButton?, isChecked: Boolean ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.btnSwitch?.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.btnSwitch?.isChecked = false
            }
        }
        return root
    }

}