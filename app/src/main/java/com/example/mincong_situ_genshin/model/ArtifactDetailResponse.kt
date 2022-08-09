package com.example.mincong_situ_genshin.model

data class ArtifactDetailResponse(
    val artifactName: String,
    val maxRarity: Int,
    val twoPieceBonus: String,
    val fourPieceBonus: String
)