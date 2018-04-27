package com.gitrepo.geektree.lithogitrepo.ViewModels

import com.gitrepo.geektree.lithogitrepo.Models.Repo
import com.gitrepo.geektree.lithogitrepo.Providers.RepoListProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RepoViewModel(repo: Repo) {

    // input
    val openProfilePublisher = PublishSubject.create<Void>()

    // output
    val openProfileObserver: Observable<Repo?>
    val profileURL: Observable<String>
    val username: Observable<String>
    var desc: Observable<String>

    private val repoId: Int

    init {
        this.repoId = repo.id ?: -1

        RepoListProvider.addAndUpdateRepo(repo)

        val repoObservable = RepoListProvider.observable(this.repoId).share()

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
            return@flatMap Observable.just(RepoListProvider.getRepo(this.repoId))
        })
    }
}