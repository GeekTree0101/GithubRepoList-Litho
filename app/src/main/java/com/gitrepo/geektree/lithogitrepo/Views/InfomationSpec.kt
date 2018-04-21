package com.gitrepo.geektree.lithogitrepo.Views
import android.graphics.Color
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.Models.Repo

object InformationSpec {
    private const val usernameFontSize: Int = 40
    private const val descriptionFontSize: Int = 30
    private const val informationSpacing: Int = 10

    fun spec(c: ComponentContext, @Prop repo: Repo): Column.Builder {
        val usernameLayout = this.usernameLayoutSpec(c, repo)
        val descLayout = this.descriptionLayoutSpec(c, repo)

        usernameLayout.marginPx(YogaEdge.BOTTOM, this.informationSpacing)

        return Column.create(c)
                .child(usernameLayout.build())
                .child(descLayout.build())
                .alignItems(YogaAlign.STRETCH)
                .alignContent(YogaAlign.STRETCH)
    }

    private fun usernameLayoutSpec(c: ComponentContext, @Prop repo: Repo): Text.Builder {
        val username: CharSequence = repo.user?.username ?: ""
        return Text.create(c)
                .text(username)
                .textColor(Color.DKGRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .textSizePx(this.usernameFontSize)
    }

    private fun descriptionLayoutSpec(c: ComponentContext, @Prop repo: Repo): Text.Builder {
        val desc = repo.desc
        return Text.create(c)
                .text(desc)
                .textColor(Color.GRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .textSizePx(this.descriptionFontSize)
    }
}