package com.example.mincong_situ_genshin.api

import com.example.mincong_situ_genshin.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GenshinRepository {
    suspend fun getCharacterNames(): Flow<UIState>
    suspend fun getCharacter(characterName: String): Flow<UIState>
    suspend fun getWeaponNames(): Flow<UIState>
    suspend fun getWeapon(weaponName: String): Flow<UIState>
    suspend fun getArtifactNames(): Flow<UIState>
    suspend fun getArtifact(artifactName: String): Flow<UIState>
}

class GenshinRepositoryImpl(private val service: GenshinService) : GenshinRepository{

    override suspend fun getCharacterNames(): Flow<UIState> =
        flow {
            //channel to emit/push data to whoever needs it
            try {
                val response = service.getCharacterNames()
                if (response.isSuccessful) {
                    //emit
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getCharacter(characterName: String): Flow<UIState> =
        flow {
            //channel to emit/push data to whoever needs it
            try {
                val response = service.getCharacter(characterName)
                if (response.isSuccessful) {
                    //emit
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getWeaponNames(): Flow<UIState> =
        flow {
            //channel to emit/push data to whoever needs it
            try {
                val response = service.getWeaponNames()
                if (response.isSuccessful) {
                    //emit
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getWeapon(weaponName: String): Flow<UIState> =
        flow {
            //channel to emit/push data to whoever needs it
            try {
                val response = service.getWeapon(weaponName)
                if (response.isSuccessful) {
                    //emit
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getArtifactNames(): Flow<UIState> =
        flow {
            //channel to emit/push data to whoever needs it
            try {
                val response = service.getArtifactNames()
                if (response.isSuccessful) {
                    //emit
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getArtifact(artifactName: String): Flow<UIState> =
        flow {
            //channel to emit/push data to whoever needs it
            try {
                val response = service.getArtifact(artifactName)
                if (response.isSuccessful) {
                    //emit
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }
}