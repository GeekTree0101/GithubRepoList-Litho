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
object InformationSpec {

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop repo: Repo): Component {
        val usernameLayout = this.usernameLayout(c, repo)
        val descLayout = this.descriptionLayout(c, repo)

        return Column.create(c)
                .child(usernameLayout)
                .child(descLayout)
                .marginPx(YogaEdge.ALL, 20)
                .alignItems(YogaAlign.STRETCH)
                .alignContent(YogaAlign.STRETCH)
                .build()
    }

    fun usernameLayout(c: ComponentContext, @Prop repo: Repo): Component {
        val username: CharSequence = repo.user?.username ?: ""
        return Text.create(c)
                .text(username)
                .textColor(Color.GRAY)
                .textSizePx(40)
                .marginPx(YogaEdge.ALL, 10)
                .build()
    }

    fun descriptionLayout(c: ComponentContext, @Prop repo: Repo): Component {
        val desc = repo.desc
        return Text.create(c)
                .text(desc)
                .textColor(Color.GRAY)
                .textSizePx(30)
                .marginPx(YogaEdge.ALL, 10)
                .build()
    }
}