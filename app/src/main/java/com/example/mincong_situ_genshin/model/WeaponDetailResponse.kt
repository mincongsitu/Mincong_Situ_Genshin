package com.example.mincong_situ_genshin.model

data class WeaponDetailResponse(
    val weaponName: String,
    val type: String,
    val rarity: Int,
    val baseAttack: Int,
    val subStat: String,
    val pName: String,
    val description: String,
    val location: String
)