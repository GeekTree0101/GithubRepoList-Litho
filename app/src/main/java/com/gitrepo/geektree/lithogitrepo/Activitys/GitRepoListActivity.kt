package com.gitrepo.geektree.lithogitrepo.Activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.Views.ProfileImageSpec

class GitRepoListActivity : AppCompatActivity() {

    private val profileImageURL: String = "http://i.imgur.com/IJF8x9D.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: ComponentContext = ComponentContext(this)

        val profileImageView = ProfileImageSpec
                .onCreateLayout(context, this.profileImageURL)

        val labelView = Text.create(context)
                .text("Cat")
                .textSizePx(120)
                .build()

        val stackLayout = Column.create(context)
                .paddingPx(YogaEdge.TOP, 100)
                .alignContent(YogaAlign.CENTER)
                .alignItems(YogaAlign.CENTER)
                .child(profileImageView)
                .child(labelView)
                .build()

        this.setContentView(LithoView.create(context, stackLayout))
    }
}