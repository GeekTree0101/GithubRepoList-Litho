package com.gitrepo.geektree.lithogitrepo.Models

class Repo {
    var id: Int? = null

    val user: User?
        get() = this.owner

    val title: String?
        get() = this.full_name

    val desc: String?
        get() = this.description

    val isPrivate: Boolean
        get() = this.private ?: false

    val isForked: Boolean
        get() = this.fork ?: false

    // object attributes
    private var owner: User? = null
    private var full_name: String? = null
    private var description: String? = null
    private var private: Boolean? = null
    private var fork: Boolean? = null
}