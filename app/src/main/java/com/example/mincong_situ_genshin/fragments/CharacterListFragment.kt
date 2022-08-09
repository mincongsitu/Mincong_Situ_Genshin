package com.example.mincong_situ_genshin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mincong_situ_genshin.controllers.CharacterAdapter
import com.example.mincong_situ_genshin.databinding.CharacterListBinding
import com.example.mincong_situ_genshin.model.UIState

class CharacterListFragment: ViewModelFragment() {

    private lateinit var binding: CharacterListBinding

    private val characterAdapter by lazy {
        CharacterAdapter(openDetails = ::openDetails)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver(){
        viewModel.characterListData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getCharacterNames()
                }
                is UIState.Error -> {
                    binding.tvErrorText.text = uiState.error.message
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        characterAdapter.setCharacterList((uiState.response as List<String>))
                        binding.rvCharacterList.adapter = characterAdapter
                    }

                }
            }
        }
    }

    private fun openDetails(character: String){
        viewModel.setCharacterDetailsLoading()
        findNavController().navigate(
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(character)

        )
    }

}