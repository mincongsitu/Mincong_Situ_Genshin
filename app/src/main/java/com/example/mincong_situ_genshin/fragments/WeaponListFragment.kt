package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mincong_situ_genshin.controllers.WeaponAdapter
import com.example.mincong_situ_genshin.databinding.WeaponListBinding
import com.example.mincong_situ_genshin.model.UIState

class WeaponListFragment: ViewModelFragment() {
    private lateinit var binding: WeaponListBinding

    private val weaponAdapter by lazy {
        WeaponAdapter(openDetails = ::openDetails)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeaponListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver(){
        viewModel.weaponListData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getWeaponNames()
                }
                is UIState.Error -> {
                    binding.tvErrorText.text = uiState.error.message
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        weaponAdapter.setWeaponList((uiState.response as List<String>))
                        binding.rvWeaponList.adapter = weaponAdapter
                    }

                }
            }
        }
    }

    private fun openDetails(weapon: String){
        viewModel.setWeaponDetailsLoading()
        findNavController().navigate(
            WeaponListFragmentDirections.actionWeaponListFragmentToWeaponDetailFragment2(weapon)

        )
    }
}