package com.gitrepo.geektree.lithogitrepo.Activitys

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.Agents.RepoAPI

import com.gitrepo.geektree.lithogitrepo.Views.ProfileImageSpec
import com.gitrepo.geektree.lithogitrepo.Views.RepoCellSpec

class GitRepoListActivity : AppCompatActivity() {

    private val profileImageURL: String = "http://i.imgur.com/IJF8x9D.jpg"

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
        this.setContentView(LithoView.create(context, this.recyclerView))

        RepoAPI.loadRepoList {
            it.forEach {
                val repoCell = RepoCellSpec.onCreateLayout(this.context, it)
                this.recyclerBinder.appendItem(repoCell)
            }
        }
    }
}