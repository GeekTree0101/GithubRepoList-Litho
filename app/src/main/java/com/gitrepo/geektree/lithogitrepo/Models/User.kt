package com.gitrepo.geektree.lithogitrepo.Models

class User {
    val username: String?
        get() = this.login

    val profileURLString: String?
        get() = this.avatar_url

    // object attributes
    private var login: String? = null
    private var avatar_url: String? = null
}