package com.gitrepo.geektree.lithogitrepo.Activitys

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.gitrepo.geektree.lithogitrepo.Providers.RepoListProvider
import com.gitrepo.geektree.lithogitrepo.ViewModels.RepoViewModel
import com.gitrepo.geektree.lithogitrepo.Views.RepoShow

class GitRepoShowActivity: AppCompatActivity() {

    companion object {
        const val REPO_ID_INTENT_KEY: String = "REPO_ID"
    }

    private val context: ComponentContext by lazy {
        ComponentContext(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.statusBarColor = Color.DKGRAY
        this.window.navigationBarColor = Color.DKGRAY
        this.supportActionBar.let {
            it?.let {
                it.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
                it.setDisplayShowTitleEnabled(true)
            }
        }

        val repoID = this.intent.extras.getInt(GitRepoShowActivity.REPO_ID_INTENT_KEY)
        RepoListProvider.getRepo(repoID)?.let {
            val viewModel = RepoViewModel(it)
            val repoShowLayout = RepoShow.create(this.context).viewModel(viewModel)
            this.setContentView(LithoView.create(this.context, repoShowLayout.build()))
        }

    }
}