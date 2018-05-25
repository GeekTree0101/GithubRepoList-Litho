package com.gitrepo.geektree.lithogitrepo.Activitys

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.gitrepo.geektree.lithogitrepo.Agents.RepoAPI
import com.gitrepo.geektree.lithogitrepo.ViewModels.RepoViewModel
import com.gitrepo.geektree.lithogitrepo.Views.RepoCell
import com.kaopiz.kprogresshud.KProgressHUD
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


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

    private val disposeBag = CompositeDisposable()

    private var repoList: ArrayList<RepoViewModel> = arrayListOf()

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

    override fun onDestroy() {
        super.onDestroy()

        // deallocate viewModels
        for (viewModel in repoList) {
            viewModel.destroy()
        }

        disposeBag.clear()
    }

    private fun loadRepoList() {
        activityIndicator.show()

        val repoObserver = RepoAPI.loadRepoObserver()
                .subscribeBy(onNext = {

                    it.forEach {
                        val viewModel = RepoViewModel(it)
                        this.repoList.add(viewModel)
                        val repoCell = RepoCell.create(context).viewModel(viewModel)

                        val openProfileDisposable = viewModel.openProfileObserver
                                .subscribeBy(onNext = {
                                    this.openProfile(it.first, it.second)
                                })

                        disposeBag.add(openProfileDisposable)

                        this.recyclerBinder.appendItem(repoCell.build())
                    }
                    activityIndicator.dismiss()
        })

        disposeBag.add(repoObserver)
    }

    fun openProfile(c: Context, id: Int) {
        val intent = Intent(c.applicationContext, GitRepoShowActivity::class.java)
        intent.putExtra(GitRepoShowActivity.REPO_ID_INTENT_KEY, id)
        c.startActivity(intent)
    }
}