package com.gitrepo.geektree.lithogitrepo.Providers
import com.gitrepo.geektree.lithogitrepo.Models.Repo
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object RepoProvider {
    private var repoList = mutableMapOf<Int, Repo>()

    fun addOrUpdateRepo(repo: Repo) {
        this.repoList.set(repo.id ?: -1, repo)
    }

    fun getRepo(id: Int): Repo? {
        return this.repoList[id]
    }
}

object RepoListProvider {
    private val repoListBehavior = BehaviorSubject.create<MutableMap<Int, Repo>>()


    fun addAndUpdateRepo(repo: Repo): Repo? {
        val id = repo.id ?: -1
        return this.repoListBehavior.value.put(id, repo)
    }

    fun getRepo(id: Int): Repo? {
        return this.repoListBehavior.value.get(id)
    }

    fun observable(id: Int): Observable<Repo?> {
        return this.repoListBehavior
                .flatMap {
                    val repo = it.get(id)
                    return@flatMap Observable.just(repo)
                }
    }

    fun update(id: Int, repo: Repo) {
        return this.repoListBehavior.value.set(id, repo)
    }
}



