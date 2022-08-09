package com.example.mincong_situ_genshin.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mincong_situ_genshin.databinding.CharacterItemBinding

class WeaponAdapter(
    private val list: MutableList<String> = mutableListOf(),
    private val openDetails: (String) -> Unit
): RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {

    fun setWeaponList(newList: List<String>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class WeaponViewHolder(private val binding: CharacterItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun onBind(weapon: String){
            binding.apply {
                tvCharacterName.text = weapon

                if(weapon.contains(" ")){
                    weapon.replace(" ", "-")
                }

                val baseUrl = "https://api.genshin.dev/weapons/"
                Glide.with(ivCharacterPhoto)
                    .load("$baseUrl$weapon/icon")
                    .into(ivCharacterPhoto)
            }

            binding.root.setOnClickListener{
                openDetails(weapon)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {

        return WeaponViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}