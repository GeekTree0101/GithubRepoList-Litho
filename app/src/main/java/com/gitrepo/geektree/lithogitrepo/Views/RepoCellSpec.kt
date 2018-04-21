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
        val url: String = repo.user?.profileURLString ?: ""

        val profileLayout = ProfileImageSpec.spec(c, url)
        val informationLayout = InformationSpec.spec(c, repo)

        profileLayout.flexShrink(1.0f)

        informationLayout
                .marginPx(YogaEdge.LEFT, 30)
                .flexShrink(1.0f)

        return Row.create(c)
                .paddingPx(YogaEdge.ALL, 20)
                .child(profileLayout.build())
                .child(informationLayout.build())
                .alignContent(YogaAlign.STRETCH)
                .alignItems(YogaAlign.CENTER)
                .build()
    }
}