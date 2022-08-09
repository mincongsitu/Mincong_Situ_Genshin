package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mincong_situ_genshin.controllers.ArtifactAdapter
import com.example.mincong_situ_genshin.databinding.ArtifactListBinding
import com.example.mincong_situ_genshin.model.UIState

class ArtifactListFragment: ViewModelFragment() {
    private lateinit var binding: ArtifactListBinding

    private val artifactAdapter by lazy {
        ArtifactAdapter(openDetails = ::openDetails)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArtifactListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver(){
        viewModel.artifactListData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getArtifactNames()
                }
                is UIState.Error -> {
                    binding.tvErrorText.text = uiState.error.message
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        artifactAdapter.setArtifactList((uiState.response as List<String>))
                        binding.rvArtifactList.adapter = artifactAdapter
                    }

                }
            }
        }
    }

    private fun openDetails(artifact: String){
        viewModel.setArtifactDetailsLoading()
        findNavController().navigate(
            ArtifactListFragmentDirections.actionArtifactListFragment2ToArtifactDetailFragment(artifact)

        )
    }
}