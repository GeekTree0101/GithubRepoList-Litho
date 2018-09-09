package com.gitrepo.geektree.lithogitrepo.Models

class Repo {
    var id: Int = -1

    val user: User?
        get() = this.owner

    val title: String?
        get() = this.full_name

    var desc: String?
        get() = this.description
        set(newDesc) {
            this.description = newDesc
        }

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