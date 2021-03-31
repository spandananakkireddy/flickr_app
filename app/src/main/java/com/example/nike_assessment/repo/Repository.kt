package com.example.nike_assessment.repo

import androidx.lifecycle.MutableLiveData
import com.example.nike_assessment.model.Items
import com.example.nike_assessment.network.RetrofitClient
import com.example.nike_assessment.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var service: RetrofitService? = null

    init {
        service = RetrofitClient.retrofitService
    }

    //Make network call to get the images
    fun getImageItems(keyword: String): MutableLiveData<Items> {
        val items = MutableLiveData<Items>()
        service!!.getImages("1", "any", "json", keyword)
                .enqueue(object : Callback<Items> {
                    override fun onResponse(call: Call<Items>, response: Response<Items>) {
                        if (response.isSuccessful) items.value = response.body()
                    }

                    override fun onFailure(call: Call<Items>, t: Throwable) {
                        items.value = null
                    }
                })
        return items
    }


    companion object {
        private var repository: Repository? = null
        fun getInstance(): Repository? {
            if (repository == null) {
                repository = Repository()
            }
            return repository
        }
    }
}

