package com.tugceaksoy.vitrinapp.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugceaksoy.vitrinapp.data.model.Output
import com.tugceaksoy.vitrinapp.data.model.discover.DiscoverModel
import com.tugceaksoy.vitrinapp.data.repository.VitrinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: VitrinRepository
) : ViewModel() {
    val postDiscover: MutableLiveData<Output<DiscoverModel>> by lazy {
        MutableLiveData<Output<DiscoverModel>>()
    }
    suspend fun getDiscover() {
        postDiscover.postValue(Output.loading(null))
        val response = repository.getDiscover()
        postDiscover.postValue(response)
    }

}