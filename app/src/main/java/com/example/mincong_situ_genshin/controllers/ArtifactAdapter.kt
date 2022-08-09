package com.example.mincong_situ_genshin.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mincong_situ_genshin.databinding.CharacterItemBinding

class ArtifactAdapter(
    private val list: MutableList<String> = mutableListOf(),
    private val openDetails: (String) -> Unit
): RecyclerView.Adapter<ArtifactAdapter.ArtifactViewHolder>() {

    fun setArtifactList(newList: List<String>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ArtifactViewHolder(private val binding: CharacterItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun onBind(artifact: String){
            binding.apply {
                tvCharacterName.text = artifact
                val baseUrl = "https://api.genshin.dev/artifacts/"
                Glide.with(ivCharacterPhoto)
                    .load("$baseUrl$artifact/goblet-of-eonothem")
                    .into(ivCharacterPhoto)

            }

            binding.root.setOnClickListener{
                openDetails(artifact)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtifactViewHolder {

        return ArtifactViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtifactViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}