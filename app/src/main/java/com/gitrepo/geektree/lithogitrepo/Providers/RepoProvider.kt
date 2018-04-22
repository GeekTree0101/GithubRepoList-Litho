package com.gitrepo.geektree.lithogitrepo.Providers

import com.gitrepo.geektree.lithogitrepo.Models.Repo

object RepoProvider {
    private var repoList = mutableMapOf<Int, Repo>()

    fun addOrUpdateRepo(repo: Repo) {
        this.repoList.set(repo.id ?: -1, repo)
    }

    fun getRepo(id: Int): Repo? {
        return this.repoList[id]
    }
}