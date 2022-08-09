package com.example.mincong_situ_genshin.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mincong_situ_genshin.databinding.CharacterItemBinding

class CharacterAdapter(
    private val list: MutableList<String> = mutableListOf(),
    private val openDetails: (String) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    fun setCharacterList(newList: List<String>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: CharacterItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun onBind(character: String){
            binding.apply {
                tvCharacterName.text = character
                val baseUrl = "https://api.genshin.dev/characters/"
                Glide.with(ivCharacterPhoto)
                    .load("$baseUrl$character/icon-big")
                    .into(ivCharacterPhoto)
            }

            binding.root.setOnClickListener{
                openDetails(character)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}