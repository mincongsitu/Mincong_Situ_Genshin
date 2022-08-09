package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mincong_situ_genshin.databinding.CharacterDetailBinding
import com.example.mincong_situ_genshin.databinding.WeaponDetailBinding
import com.example.mincong_situ_genshin.model.CharacterDetailResponse
import com.example.mincong_situ_genshin.model.UIState
import com.example.mincong_situ_genshin.model.WeaponDetailResponse

class WeaponDetailFragment: ViewModelFragment() {

    private lateinit var binding: WeaponDetailBinding
    private val args: WeaponDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeaponDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver(){
        viewModel.weaponDetailListData.observe(viewLifecycleOwner){ state ->
            when(state){
                is UIState.Loading -> {
                    viewModel.getWeapon(args.weaponName)
                }
                is UIState.Error -> {
                    binding.tvDetailError.text = state.error.message
                }
                is UIState.Success<*> -> {
                    val weaponDetail: WeaponDetailResponse = (state.response as WeaponDetailResponse)
                    binding.apply {

                        tvWeaponDetailName.text = weaponDetail.weaponName
                        tvWeaponDescription.text = weaponDetail.description
                        tvType.text = "Type: ${weaponDetail.type}"
                        rbRarity.numStars = weaponDetail.rarity
                        tvBaseAtk.text = "Base Attack: ${weaponDetail.baseAttack}"


                        if(weaponDetail.weaponName.contains(" ")){
                            weaponDetail.weaponName.replace(" ", "-")
                        }

                        Glide.with(ivWeaponImage)
                            .load("https://api.genshin.dev/weapons/${args.weaponName.lowercase()}/card")
                            .into(ivWeaponImage)

                    }
                }
            }
        }
    }
}