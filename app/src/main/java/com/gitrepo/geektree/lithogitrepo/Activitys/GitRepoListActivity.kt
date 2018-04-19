package com.gitrepo.geektree.lithogitrepo.Activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Text

class GitRepoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var context: ComponentContext = ComponentContext(this)
        val labelView = Text.create(context)
                .text("hello world")
                .textSizePx(300)
                .build()

        this.setContentView(LithoView.create(context, labelView))
    }
}