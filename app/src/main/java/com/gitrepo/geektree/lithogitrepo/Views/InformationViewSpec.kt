package com.gitrepo.geektree.lithogitrepo.Views

import android.graphics.Color
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.Models.Repo


@LayoutSpec
object InformationViewSpec {
    private const val usernameFontSize: Int = 40
    private const val descriptionFontSize: Int = 30
    private const val informationSpacing: Int = 10

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext,
                       @Prop repo: Repo,
                       @Prop(optional = true) defaultScale: Int?,
                       @Prop(optional = true) isCenterAlign: Boolean): Component {
        val scale = defaultScale ?: 1
        val usernameLayout = this.usernameLayoutSpec(c, repo, scale)
        val descLayout = this.descriptionLayoutSpec(c, repo, scale)

        usernameLayout.marginPx(YogaEdge.BOTTOM, this.informationSpacing)

        return Column.create(c)
                .child(usernameLayout.build())
                .child(descLayout.build())
                .alignItems(if (isCenterAlign) YogaAlign.CENTER else YogaAlign.STRETCH)
                .alignContent(YogaAlign.STRETCH)
                .build()
    }

    private fun usernameLayoutSpec(c: ComponentContext,
                                   repo: Repo,
                                   scale: Int): Text.Builder {
        val username: CharSequence = repo.user?.username ?: ""
        return Text.create(c)
                .text(username)
                .textColor(Color.DKGRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .textSizePx(this.usernameFontSize * scale)
    }

    private fun descriptionLayoutSpec(c: ComponentContext,
                                      repo: Repo,
                                      scale: Int): Text.Builder {
        val desc = repo.desc
        return Text.create(c)
                .text(desc)
                .textColor(Color.GRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .textSizePx(this.descriptionFontSize * scale)
    }
}