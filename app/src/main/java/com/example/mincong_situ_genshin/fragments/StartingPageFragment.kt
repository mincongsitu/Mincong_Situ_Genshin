package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mincong_situ_genshin.databinding.StartingPageBinding

class StartingPageFragment: ViewModelFragment() {

    private lateinit var binding: StartingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartingPageBinding.inflate(layoutInflater)

        binding.ibCharacterButton.setOnClickListener{
            viewModel.setCharacterLoading()
            findNavController().navigate(
                StartingPageFragmentDirections.actionStartingPageFragmentToCharacterListFragment()
            )
        }

        binding.ibWeaponButton.setOnClickListener{
            viewModel.setWeaponLoading()
            findNavController().navigate(
                StartingPageFragmentDirections.actionStartingPageFragmentToWeaponListFragment()
            )
        }

        binding.ibArtifactButton.setOnClickListener{
            viewModel.setArtifactLoading()
            findNavController().navigate(
                StartingPageFragmentDirections.actionStartingPageFragmentToArtifactListFragment2()
            )
        }

        return binding.root
    }
}