package com.gitrepo.geektree.lithogitrepo.Views
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.Models.Repo

@LayoutSpec
object RepoCellSpec {

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop repo: Repo): Component {
        val profileLayout = ProfileImageSpec
                .onCreateLayout(c, repo.user?.profileURLString ?: "")
        val informationLayout = InformationSpec.onCreateLayout(c, repo)

        return Row.create(c)
                .marginPx(YogaEdge.ALL, 10)
                .child(profileLayout)
                .child(informationLayout)
                .alignContent(YogaAlign.STRETCH)
                .alignItems(YogaAlign.CENTER)
                .build()
    }
}