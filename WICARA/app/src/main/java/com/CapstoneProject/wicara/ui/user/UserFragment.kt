package com.CapstoneProject.wicara.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.FragmentSettingBinding
import com.CapstoneProject.wicara.databinding.FragmentUserBinding
import com.CapstoneProject.wicara.sharedPreferences.UserModel
import com.CapstoneProject.wicara.sharedPreferences.UserPreferences
import com.CapstoneProject.wicara.ui.ChangeProfilActivity

class UserFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentUserBinding? = null
    private lateinit var mUserPreference: UserPreferences

    private var isPreferenceEmpty = false
    private lateinit var userModel: UserModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user, container, false)
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root : View = binding.root
        mUserPreference = UserPreferences(requireActivity())
        showExistingPreference()

        binding.cdAbout.setOnClickListener(this)
        binding.cd1.setOnClickListener(this)

        return root
    }

    private fun showExistingPreference() {
        userModel = mUserPreference.getUser()
        populateView(userModel)
    }

    private fun populateView(userModel: UserModel) {
            binding.txtNameuser.text = if (userModel.name!!.isEmpty()) "User" else userModel.name
            binding.txtEmailUser.text = if (userModel.email!!.isEmpty()) "Email@gmail.com" else userModel.email
            binding.txtNumberUser.text = if (userModel.phoneNUmber!!.isEmpty()) "+66 00 99" else userModel.phoneNUmber
            binding.cdEmail.text = if (userModel.phoneNUmber!!.isEmpty()) "Example@gmail.com" else userModel.email
            binding.cdPhone.text = if (userModel.phoneNUmber!!.isEmpty()) "+66 00 99" else userModel.phoneNUmber
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.cd_about -> {
                val url = "https://wicara2022.github.io/"
                val move = Intent(Intent.ACTION_VIEW)
                move.setData(Uri.parse(url))
                startActivity(move)
            }
            R.id.cd1 -> {
                move()
            }
        }
    }

    private fun move(){
        val move = Intent(requireActivity(), ChangeProfilActivity::class.java)
        move.putExtra("USER", userModel)
        resultLauncher.launch(move)

    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.data != null && result.resultCode == ChangeProfilActivity.RESULT_CODE) {
            userModel = result.data?.getParcelableExtra<UserModel>(ChangeProfilActivity.EXTRA_RESULT) as UserModel
            populateView(userModel)
        }
    }

}