package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mincong_situ_genshin.databinding.ArtifactDetailBinding
import com.example.mincong_situ_genshin.model.ArtifactDetailResponse
import com.example.mincong_situ_genshin.model.UIState

class ArtifactDetailFragment: ViewModelFragment() {
    private lateinit var binding: ArtifactDetailBinding
    private val args: ArtifactDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArtifactDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver(){
        viewModel.artifactDetailListData.observe(viewLifecycleOwner){ state ->
            when(state){
                is UIState.Loading -> {
                    viewModel.getArtifact(args.artifactName)
                }
                is UIState.Error -> {
                    binding.tvError.text = state.error.message
                }
                is UIState.Success<*> -> {
                    val artifactDetail: ArtifactDetailResponse = (state.response as ArtifactDetailResponse)
                    binding.apply {
                        tvArtifactName.text = artifactDetail.artifactName
                        tvTwoBonus.text = "Two Piece Bonus: ${artifactDetail.twoPieceBonus}"
                        tvFourBonus.text = "Four Piece Bonus: ${artifactDetail.fourPieceBonus}"

                        if(artifactDetail.artifactName.contains(" ")){
                            artifactDetail.artifactName.replace(" ", "-")
                        }

                        val baseUrl = "https://api.genshin.dev/artifacts/"

                        Glide.with(ivGobletOfEonothem)
                            .load("$baseUrl${artifactDetail.artifactName.lowercase()}/goblet-of-eonothem")
                            .into(ivGobletOfEonothem)
                        Glide.with(ivCircletOfLogos)
                            .load("$baseUrl${artifactDetail.artifactName.lowercase()}/circlet-of-logos")
                            .into(ivCircletOfLogos)
                        Glide.with(ivFlowerOfLife)
                            .load("$baseUrl${artifactDetail.artifactName.lowercase()}/flower-of-life")
                            .into(ivFlowerOfLife)
                        Glide.with(ivPlumeOfDeath)
                            .load("$baseUrl${artifactDetail.artifactName.lowercase()}/plume-of-death")
                            .into(ivPlumeOfDeath)
                        Glide.with(ivSandsOfEon)
                            .load("$baseUrl${artifactDetail.artifactName.lowercase()}/sands-of-eon")
                            .into(ivSandsOfEon)

                    }

                    }
                }
            }
        }
    }