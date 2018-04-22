package com.gitrepo.geektree.lithogitrepo.Activitys

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.gitrepo.geektree.lithogitrepo.Agents.RepoAPI
import com.gitrepo.geektree.lithogitrepo.Providers.RepoProvider
import com.gitrepo.geektree.lithogitrepo.Views.RepoCell
import com.kaopiz.kprogresshud.KProgressHUD


class GitRepoListActivity : AppCompatActivity() {


    private val activityIndicator: KProgressHUD by lazy {
        val progress = KProgressHUD(this)
        progress.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        progress
    }

    private val context: ComponentContext by lazy {
        ComponentContext(this)
    }

    private val recyclerBinder: RecyclerBinder by lazy {
        RecyclerBinder.Builder().build(this.context)
    }

    private val recyclerView: Recycler by lazy {
        Recycler.create(this.context)
                .binder(this.recyclerBinder)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.statusBarColor = Color.DKGRAY
        this.window.navigationBarColor = Color.DKGRAY
        this.supportActionBar.let {
            it?.let {
                it.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
            }
        }

        this.setContentView(LithoView.create(context, this.recyclerView))
        this.loadRepoList()
    }

    private fun loadRepoList() {
        activityIndicator.show()

        RepoAPI.loadRepoList {
            it.forEach {
                val repoCell = RepoCell.create(context).repo(it)
                this.recyclerBinder.appendItem(repoCell.build())
                RepoProvider.addOrUpdateRepo(it)
            }
            activityIndicator.dismiss()
        }
    }
}