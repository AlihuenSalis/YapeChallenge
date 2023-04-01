package com.example.yapechallenge.ui.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yapechallenge.domain.model.Menu
import com.example.yapechallenge.domain.usesCase.GetMenusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val getMenuUC: GetMenusUseCase
) : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isResponseOk: MutableLiveData<MutableList<Menu>?> = MutableLiveData()

    fun getAllMenus() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = getMenuUC.invoke()

            if (response.isNotEmpty()) {
                isResponseOk.postValue(response as MutableList<Menu>)
            } else {
                isResponseOk.postValue(null)
            }
            isLoading.postValue(false)

//            response.enqueue(object : Callback<ResponseApi> {
//                override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
//                    if (response.body()?.body?.isNotEmpty() == true){
//                        isLoading.postValue(false)
//                        isResponseOk.postValue(response.body()?.body as MutableList<Menu>?)
//                    } else {
//                        isResponseOk.postValue(null)
//                    }
//
//                }
//
//                override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
//                    isLoading.postValue(false)
//                    isResponseOk.postValue(null)
//                }
//            })
        }
    }
}