package com.gitrepo.geektree.lithogitrepo.Agents

import android.nfc.Tag
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.ParsedRequestListener
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener

import com.gitrepo.geektree.lithogitrepo.Models.Repo
import org.json.JSONArray




object RepoAPI {

    // route
    private val baseURL: String = "https://api.github.com"

    fun repoListURL(): String {
        return this.baseURL + "/repositories"
    }

    fun loadRepoList(complete: (List<Repo>) -> Unit) {
        AndroidNetworking.get(this.repoListURL())
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Repo::class.java,
                        object : ParsedRequestListener<List<Repo>> {
                            override fun onResponse(response: List<Repo>?) {
                                response?.let(complete)
                            }

                            override fun onError(anError: ANError?) {

                            }
                        })
    }
}
