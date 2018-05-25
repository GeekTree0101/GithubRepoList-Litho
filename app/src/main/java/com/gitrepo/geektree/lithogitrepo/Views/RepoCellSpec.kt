package com.gitrepo.geektree.lithogitrepo.Views

import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.litho.annotations.Prop
import com.facebook.litho.ClickEvent
import com.facebook.litho.annotations.OnEvent
import com.gitrepo.geektree.lithogitrepo.ViewModels.RepoViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

@LayoutSpec
object RepoCellSpec {
    private const val profileSpacingWithInformation: Int = 30
    private const val repoInset: Int = 20

    val disposeBag = CompositeDisposable()

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext,
                       @Prop viewModel: RepoViewModel): Component {

        val profileLayout = ProfileImageView
                .create(c)
                .urlBinder(viewModel.profileURL)
                .flexShrink(1.0f)

        val informationLayout = InformationView
                .create(c)
                .viewModel(viewModel)
                .marginPx(YogaEdge.LEFT, this.profileSpacingWithInformation)
                .flexShrink(1.0f)

        val repoCellLayout = Row.create(c)
                .paddingPx(YogaEdge.ALL, this.repoInset)
                .child(profileLayout)
                .child(informationLayout)
                .alignContent(YogaAlign.STRETCH)
                .alignItems(YogaAlign.CENTER)
                .clickHandler(RepoCell.didTapProfile(c))
                .build()

        return repoCellLayout
    }

    @OnEvent(ClickEvent::class)
    fun didTapProfile(c: ComponentContext, @Prop viewModel: RepoViewModel) {
        viewModel.openProfilePublisher.onNext(c)
    }
}