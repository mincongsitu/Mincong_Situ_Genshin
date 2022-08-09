package com.example.mincong_situ_genshin.api

import com.example.mincong_situ_genshin.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GenshinService {

    @GET("characters")
    suspend fun getCharacterNames(): Response<List<String>> //returns a response that holds a list of data

    @GET("characters/{characterName}")
    suspend fun getCharacter(
        @Path("characterName") characterName: String
    ): Response<CharacterDetailResponse> //returns a single response

    @GET("weapons")
    suspend fun getWeaponNames(): Response<List<String>> //returns a response that holds a list of data

    @GET("weapons/{weaponName}")
    suspend fun getWeapon(
        @Path("weaponName") weaponName: String
    ): Response<WeaponDetailResponse> //returns a single response

    @GET("artifacts")
    suspend fun getArtifactNames(): Response<List<String>> //returns a response that holds a list of data

    @GET("artifacts/{artifactName}")
    suspend fun getArtifact(
        @Path("artifactName") artifactName: String
    ): Response<ArtifactDetailResponse>



}