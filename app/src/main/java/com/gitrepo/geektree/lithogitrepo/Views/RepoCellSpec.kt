package com.gitrepo.geektree.lithogitrepo.Views

import android.content.Intent
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.Models.Repo
import com.facebook.litho.annotations.Prop
import com.facebook.litho.ClickEvent
import com.facebook.litho.annotations.OnEvent
import com.gitrepo.geektree.lithogitrepo.Activitys.GitRepoShowActivity

@LayoutSpec
object RepoCellSpec {
    private const val profileSpacingWithInformation: Int = 30
    private const val repoInset: Int = 20

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext,
                       @Prop repo: Repo): Component {
        val profileLayout = ProfileImageView
                .create(c)
                .profileURL(repo.user?.profileURLString ?: "")
                .flexShrink(1.0f)

        val informationLayout = InformationView
                .create(c)
                .repo(repo)
                .marginPx(YogaEdge.LEFT, this.profileSpacingWithInformation)
                .flexShrink(1.0f)

        return Row.create(c)
                .paddingPx(YogaEdge.ALL, this.repoInset)
                .child(profileLayout)
                .child(informationLayout)
                .alignContent(YogaAlign.STRETCH)
                .alignItems(YogaAlign.CENTER)
                .clickHandler(RepoCell.didTapProfile(c))
                .build()
    }

    @OnEvent(ClickEvent::class)
    fun didTapProfile(c: ComponentContext, @Prop repo: Repo) {
        val intent = Intent(c.applicationContext, GitRepoShowActivity::class.java)
        intent.putExtra(GitRepoShowActivity.REPO_ID_INTENT_KEY, repo.id)
        c.startActivity(intent)
    }
}