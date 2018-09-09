package com.gitrepo.geektree.lithogitrepo.ViewModels

import android.content.Context
import com.gitrepo.geektree.lithogitrepo.Models.Repo
import com.gitrepo.geektree.lithogitrepo.Providers.RepoListProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

class RepoViewModel(repo: Repo) {

    // input
    val openProfilePublisher = PublishSubject.create<Context>()
    val editUsernamePublisher = PublishSubject.create<String>()
    val didTapDescriptionPublisher = PublishSubject.create<Context>()

    // output
    val openProfileObserver: Observable<Pair<Context, Int>>
    val profileURL: Observable<String>
    val username: Observable<String>
    val desc: Observable<String>
    val openDescDialog: Observable<String>

    val repoId: Int

    val disposeBag = CompositeDisposable()

    fun destroy() {
        RepoListProvider.destory(repoId)
        disposeBag.clear()
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

        openDescDialog = didTapDescriptionPublisher
                .withLatestFrom(repoObservable, BiFunction<Context, Repo?, Repo?> { u, t -> t })
                .map { it.desc ?: "Empty "}


        openProfileObserver = openProfilePublisher.flatMap({
            return@flatMap Observable.just(Pair(it, this.repoId))
        })

        val editUsernameDisposable = editUsernamePublisher
                .withLatestFrom(repoObservable,
                        BiFunction { desc: String, repo: Repo? ->
                    repo?.desc = desc
                    repo ?: Repo()
                })
                .subscribeBy(onNext = {
                    RepoListProvider.update(it.id, it)
                })
        this.disposeBag.add(editUsernameDisposable)
    }
}