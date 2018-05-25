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
import com.gitrepo.geektree.lithogitrepo.ViewModels.RepoViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


@LayoutSpec
object InformationViewSpec {
    private const val usernameFontSize: Int = 40
    private const val descriptionFontSize: Int = 30
    private const val informationSpacing: Int = 10

    val disposeBag = CompositeDisposable()

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext,
                       @Prop viewModel: RepoViewModel,
                       @Prop(optional = true) defaultScale: Int?,
                       @Prop(optional = true) isCenterAlign: Boolean): Component {
        val scale = defaultScale ?: 1
        val usernameLayout = this.usernameLayoutSpec(c, scale)
        val descLayout = this.descriptionLayoutSpec(c, scale)

        usernameLayout.marginPx(YogaEdge.BOTTOM, this.informationSpacing)

        val usernameDisposable = viewModel.username.subscribeBy(onNext = {
            usernameLayout.text(it)
        })

        val descDisposable = viewModel.desc.subscribeBy(onNext = {
            descLayout.text(it)
        })

        disposeBag.add(usernameDisposable)
        disposeBag.add(descDisposable)

        return Column.create(c)
                .child(usernameLayout.build())
                .child(descLayout.build())
                .alignItems(if (isCenterAlign) YogaAlign.CENTER else YogaAlign.STRETCH)
                .alignContent(YogaAlign.STRETCH)
                .build()
    }

    private fun usernameLayoutSpec(c: ComponentContext,
                                   scale: Int): Text.Builder {
        return Text.create(c)
                .text("")
                .textColor(Color.DKGRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .textSizePx(this.usernameFontSize * scale)
    }

    private fun descriptionLayoutSpec(c: ComponentContext,
                                      scale: Int): Text.Builder {
        return Text.create(c)
                .text("")
                .textColor(Color.GRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .textSizePx(this.descriptionFontSize * scale)
    }
}