package com.gitrepo.geektree.lithogitrepo.Providers
import com.gitrepo.geektree.lithogitrepo.Models.Repo
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

object RepoListProvider {
    private val repoListBehavior: BehaviorSubject<MutableMap<Int, Repo>> =
            BehaviorSubject.createDefault(mutableMapOf())

    fun addAndUpdateRepo(repo: Repo): Repo? {
        val id = repo.id ?: -1
        this.repoListBehavior.value.set(id, repo)
        return this.repoListBehavior.value[id]
    }

    fun getRepo(id: Int): Repo? {
        return this.repoListBehavior.value[id]
    }

    fun destory(id: Int) {
        this.repoListBehavior.value.remove(id)
    }

    fun observable(id: Int): Observable<Repo?> {
        return this.repoListBehavior
                .flatMap {
                    val repo = it.get(id) ?: Repo()
                    return@flatMap Observable.just(repo)
                }
    }

    fun update(id: Int, repo: Repo) {
        return this.repoListBehavior.value.set(id, repo)
    }
}



