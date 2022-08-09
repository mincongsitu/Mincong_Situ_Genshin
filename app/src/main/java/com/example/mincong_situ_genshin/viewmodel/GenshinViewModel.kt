package com.example.mincong_situ_genshin.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mincong_situ_genshin.api.GenshinRepository
import com.example.mincong_situ_genshin.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class GenshinViewModel(
    private val repository: GenshinRepository,
    private val dispatcher: CoroutineDispatcher
):ViewModel() {

    private val _characterListData = MutableLiveData<UIState>()
    val characterListData: LiveData<UIState> get() = _characterListData

    private val _weaponListData = MutableLiveData<UIState>()
    val weaponListData: LiveData<UIState> get() = _weaponListData

    private val _artifactListData = MutableLiveData<UIState>()
    val artifactListData: LiveData<UIState> get() = _artifactListData

    private val _characterDetailListData = MutableLiveData<UIState>()
    val characterDetailListData: LiveData<UIState> get() = _characterDetailListData

    private val _weaponDetailListData = MutableLiveData<UIState>()
    val weaponDetailListData: LiveData<UIState> get() = _weaponDetailListData

    private val _artifactDetailListData = MutableLiveData<UIState>()
    val artifactDetailListData: LiveData<UIState> get() = _artifactDetailListData



    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                ContentValues.TAG,
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getCharacterNames(){
        viewModelSafeScope.launch(dispatcher) {
            // Collect from our flow
            repository.getCharacterNames().collect { state ->
                // post value update LiveData asynchronously
                _characterListData.postValue(state)
            }
        }
    }

    fun getCharacter(name: String){
        viewModelSafeScope.launch(dispatcher) {
            // Collect from our flow
            repository.getCharacter(name).collect { state ->
                // post value update LiveData asynchronously
                _characterDetailListData.postValue(state)
            }
        }
    }

    fun getWeaponNames(){
        viewModelSafeScope.launch(dispatcher) {
            // Collect from our flow
            repository.getWeaponNames().collect { state ->
                // post value update LiveData asynchronously
                _weaponListData.postValue(state)
            }
        }
    }

    fun getWeapon(name: String){
        viewModelSafeScope.launch(dispatcher) {
            // Collect from our flow
            repository.getWeapon(name).collect { state ->
                // post value update LiveData asynchronously
                _weaponDetailListData.postValue(state)
            }
        }
    }

    fun getArtifactNames(){
        viewModelSafeScope.launch(dispatcher) {
            // Collect from our flow
            repository.getArtifactNames().collect { state ->
                // post value update LiveData asynchronously
                _artifactListData.postValue(state)
            }
        }
    }

    fun getArtifact(name: String){
        viewModelSafeScope.launch(dispatcher) {
            // Collect from our flow
            repository.getArtifact(name).collect { state ->
                // post value update LiveData asynchronously
                _characterListData.postValue(state)
            }
        }
    }


    fun setCharacterLoading() {
        _characterListData.value = UIState.Loading
    }

    fun setCharacterDetailsLoading() {
        _characterDetailListData.value = UIState.Loading
    }

    fun setWeaponLoading() {
        _weaponListData.value = UIState.Loading
    }

    fun setWeaponDetailsLoading() {
        _weaponDetailListData.value = UIState.Loading
    }

    fun setArtifactLoading() {
        _artifactListData.value = UIState.Loading
    }

    fun setArtifactDetailsLoading() {
        _artifactDetailListData.value = UIState.Loading
    }



}