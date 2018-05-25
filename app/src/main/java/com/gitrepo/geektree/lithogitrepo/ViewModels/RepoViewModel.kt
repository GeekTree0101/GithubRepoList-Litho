package com.gitrepo.geektree.lithogitrepo.ViewModels

import android.content.Context
import com.gitrepo.geektree.lithogitrepo.Models.Repo
import com.gitrepo.geektree.lithogitrepo.Providers.RepoListProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RepoViewModel(repo: Repo) {

    // input
    val openProfilePublisher = PublishSubject.create<Context>()

    // output
    val openProfileObserver: Observable<Pair<Context, Int>>
    val profileURL: Observable<String>
    val username: Observable<String>
    val desc: Observable<String>

    val repoId: Int

    fun destroy() {
        RepoListProvider.destory(repoId)
    }

    init {
        this.repoId = repo.id

        RepoListProvider.addAndUpdateRepo(repo)

        val repoObservable = RepoListProvider
                .observable(this.repoId)

        profileURL = repoObservable
                .map { it.user?.profileURLString ?: "" }
                .filter { !it.isEmpty() }
                .distinctUntilChanged()

        username = repoObservable
                .map { it.user?.username ?: "" }
                .filter { !it.isEmpty() }
                .distinctUntilChanged()

        desc = repoObservable
                .map { it.desc ?: "" }
                .filter { !it.isEmpty() }
                .distinctUntilChanged()

        openProfileObserver = openProfilePublisher.flatMap({
            return@flatMap Observable.just(Pair(it, this.repoId))
        })
    }
}