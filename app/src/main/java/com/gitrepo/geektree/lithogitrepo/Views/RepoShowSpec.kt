package com.gitrepo.geektree.lithogitrepo.Views

import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.ViewModels.RepoViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

@LayoutSpec
object RepoShowSpec {

    private const val topInset: Int = 50
    private const val sideInset: Int = 20
    private const val profileBottomSpacing: Int = 40

    val disposeBag = CompositeDisposable()

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop viewModel: RepoViewModel): Component {
        val profileLayout = ProfileImageView
                .create(c)
                .urlBinder(viewModel.profileURL)
                .defaultScale(3)
                .paddingPx(YogaEdge.BOTTOM, this.profileBottomSpacing)

        val infoLayout = InformationView.create(c)
                .viewModel(viewModel)
                .isEditable(true)
                .defaultScale(2)
                .isCenterAlign(true)
                .alignSelf(YogaAlign.CENTER)

        return Column.create(c)
                .child(profileLayout)
                .child(infoLayout)
                .alignItems(YogaAlign.CENTER)
                .paddingPx(YogaEdge.TOP, this.topInset)
                .paddingPx(YogaEdge.LEFT, this.sideInset)
                .paddingPx(YogaEdge.RIGHT, this.sideInset)
                .build()
    }
}