package com.gitrepo.geektree.lithogitrepo.Agents
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.ParsedRequestListener
import com.androidnetworking.error.ANError
import com.gitrepo.geektree.lithogitrepo.Models.Repo
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.rxkotlin.Observables
import java.util.*

object RepoAPI {

    // route
    private val baseURL: String = "https://api.github.com"

    private fun repoListURL(): String {
        return this.baseURL + "/repositories"
    }

    // service
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

    fun loadRepoObserver(): Observable<List<Repo>> {
        return Observable.create<List<Repo>>({
            AndroidNetworking.get(this.repoListURL())
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsObjectList(Repo::class.java,
                            object : ParsedRequestListener<List<Repo>> {
                                override fun onResponse(response: List<Repo>?) {
                                    it.onNext(response.orEmpty())
                                    it.onComplete()

                                }
                                override fun onError(anError: ANError?) {
                                    it.onError(Throwable())
                                }
                            })
        })
    }
}
