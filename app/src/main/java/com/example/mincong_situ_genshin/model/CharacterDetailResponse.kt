package com.example.mincong_situ_genshin.model

data class CharacterDetailResponse(
    val name: String,
    val vision: String,
    val weapon: String,
    val nation: String,
    val description: String?,
    val rarity: Int,
    val constellation: String,
    val skills: List<SkillTalents>,
    val passive: List<PassiveTalents>,
    val constellationSkills: List<Constellations>
)

data class SkillTalents(
    val sName: String,
    val sType: String, //"unlock" keyword
    val sDescription: String
)

data class PassiveTalents(
    val pName: String,
    val pType: String,
    val pDescription: String
)

data class Constellations(
    val cName: String,
    val cType: String,
    val cDescription: String,
    val cLevel: Int
)

