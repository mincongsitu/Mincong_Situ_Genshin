package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mincong_situ_genshin.databinding.CharacterDetailBinding
import com.example.mincong_situ_genshin.model.CharacterDetailResponse
import com.example.mincong_situ_genshin.model.UIState

class CharacterDetailFragment: ViewModelFragment() {

    private lateinit var binding: CharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver(){
        viewModel.characterDetailListData.observe(viewLifecycleOwner){ state ->
            when(state){
                is UIState.Loading -> {
                    viewModel.getCharacter(args.characterName)
                }
                is UIState.Error -> {
                    binding.tvDetailError.text = state.error.message
                }
                is UIState.Success<*> -> {
                    val characterDetail: CharacterDetailResponse = (state.response as CharacterDetailResponse)
                    binding.apply {

                        tvCharacterDetailName.text = characterDetail.name
                        tvCharacterDescription.text = characterDetail.description
                        tvVision.text = "Vision: ${characterDetail.vision}"
                        tvWeapon.text = "Weapon: ${characterDetail.weapon}"
                        tvConstellation.text = "Constellation: ${characterDetail.constellation}"
                        rbRarity.numStars = characterDetail.rarity


                        if(characterDetail.name.contains(" ")){
                            characterDetail.name.replace(" ", "-")
                        }

                        Glide.with(ivCharacterImage)
                            .load("https://api.genshin.dev/characters/${args.characterName.lowercase()}/card")
                            .into(ivCharacterImage)

                        Glide.with(ivConstellation)
                            .load("https://api.genshin.dev/characters/${args.characterName.lowercase()}/constellation")
                            .into(ivConstellation)

                        ivCharacterImage.setOnClickListener{

                            ivCharacterImage.visibility = View.GONE
                            ibBackToCard.visibility = View.VISIBLE
                            ivConstellation.visibility = View.VISIBLE
                            tvCharacterDetailName.visibility = View.VISIBLE
                            tvCharacterDescription.visibility = View.VISIBLE
                            tvVision.visibility = View.VISIBLE
                            tvWeapon.visibility = View.VISIBLE
                            tvConstellation.visibility = View.VISIBLE
                            }

                        ibBackToCard.setOnClickListener{
                            ivCharacterImage.visibility = View.VISIBLE
                            ivConstellation.visibility = View.GONE
                            tvCharacterDetailName.visibility = View.GONE
                            tvCharacterDescription.visibility = View.GONE
                            tvVision.visibility = View.GONE
                            tvWeapon.visibility = View.GONE
                            ibBackToCard.visibility = View.GONE
                            tvConstellation.visibility = View.GONE
                        }




                    }
                }
            }
        }
    }
}