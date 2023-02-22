package com.example.combinedrecycleview.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.combinedrecycleview.model.JobsResponse
import com.example.combinedrecycleview.network.ApiService
import com.example.combinedrecycleview.network.WebserviceCaller
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private lateinit var subscription: Disposable
    private var apiService: ApiService = WebserviceCaller.getClient()

    var mutCombinedJobsResponse: MutableLiveData<JobsResponse> = MutableLiveData()
    var pageNumber: Int = 1
    var size: Int = 1

    /**     Get Location **/
    fun onGetCombinedJobs(
        selectedSkillsId: ArrayList<Int> = ArrayList(),
    ) {
        val map = HashMap<String, Any>()
        selectedSkillsId.add(10)
        selectedSkillsId.add(54)
        map["version"] = "2"
        map["latlng"] = "72.95565, 22.55788"
        map["skillIds"] = selectedSkillsId
        map["defaultSkills"] = true
        map["pageNumber"] = pageNumber
        map["size"] = size

        val json = JsonParser().parse(Gson().toJson(map)) as JsonObject
        val token =
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTg2NjYyODUxIiwiaXNzIjoiSk9CX1NFRUtFUiIsImlhdCI6MTY3NjI3MjQ5NiwiZXhwIjoxNjgwMTYwNDk2LCJ1c2VyLWRldGFpbHMiOjE3Mn0.88Nvv8owFkBNCqxu1-PfpH1gdQlzxri7gG0dhzClN24CrkMOyFdTLzukxc72inpaB3mEFnxEIkk-lAIoHVmLcT"
        subscription = apiService.onGetCombinedJobs(token, "172", json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(
                { result -> onGetJobsSuccess(result) },
                { error -> onCommonError(error) }
            )
    }

    private fun onCommonError(error: Throwable?) {
        Log.e("DFSXDFFFFDFDFDF", "error " + error?.message)
    }

    private fun onGetJobsSuccess(result: JobsResponse?) {
        mutCombinedJobsResponse.value = result
    }
}