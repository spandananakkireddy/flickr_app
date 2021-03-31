package com.example.nike_assessment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nike_assessment.model.Items
import com.example.nike_assessment.repo.Repository

class ImageViewModel : ViewModel() {

    private var _imageItems = MutableLiveData<Items>()
    private var repository: Repository? = null

    fun init(keyword: String){
        repository = Repository.getInstance()
        _imageItems = repository!!.getImageItems(keyword)
    }

    fun getImages(): LiveData<Items> {
        return _imageItems
    }

}
