package com.gitrepo.geektree.lithogitrepo.Views

import android.graphics.Color
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.EditText
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.TextChangedEvent
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.gitrepo.geektree.lithogitrepo.ViewModels.RepoViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject


@LayoutSpec
object InformationViewSpec {
    private const val usernameFontSize: Int = 40
    private const val descriptionFontSize: Int = 30
    private const val informationSpacing: Int = 10

    val disposeBag = CompositeDisposable()
    val editPublisher = PublishSubject.create<String>()

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext,
                       @Prop viewModel: RepoViewModel,
                       @Prop isEditable: Boolean,
                       @Prop(optional = true) defaultScale: Int?,
                       @Prop(optional = true) isCenterAlign: Boolean): Component {
        val scale = defaultScale ?: 1
        val descLayout = this.descriptionLayoutSpec(c, scale)

        val descDisposable =
                viewModel.desc.subscribeBy(onNext = {
                    descLayout.text(it)
                })

        if (isEditable) {
            val usernameLayout =
                    this.editableUsernameLayoutSpec(c, viewModel.editUsernamePublisher, scale)

            usernameLayout.marginPx(YogaEdge.BOTTOM, this.informationSpacing)

            val usernameDisposable =
                    viewModel.username
                            .subscribeBy(onNext = {
                                usernameLayout.text(it)
                            })

            disposeBag.add(usernameDisposable)
            disposeBag.add(descDisposable)

            return Column.create(c)
                    .child(usernameLayout.build())
                    .child(descLayout.build())
                    .alignItems(if (isCenterAlign) YogaAlign.CENTER else YogaAlign.STRETCH)
                    .alignContent(YogaAlign.STRETCH)
                    .build()
        } else {
            val usernameLayout = this.usernameLayoutSpec(c, scale)
            usernameLayout.marginPx(YogaEdge.BOTTOM, this.informationSpacing)

            val usernameDisposable = viewModel.username.subscribeBy(onNext = {
                usernameLayout.text(it)
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

    private fun editableUsernameLayoutSpec(c: ComponentContext,
                                           editBinder: PublishSubject<String>,
                                           scale: Int): EditText.Builder {
        val editDisposable = this.editPublisher.subscribeBy(onNext = {
            editBinder.onNext(it)
        })

        this.disposeBag.add(editDisposable)

        return EditText.create(c)
                .text("")
                .textColor(Color.DKGRAY)
                .flexShrink(1.0f)
                .flexGrow(0.0f)
                .editable(true)
                .textChangedEventHandler(InformationView.onUsernameChanged(c))
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

    @OnEvent(TextChangedEvent::class)
    fun onUsernameChanged(c: ComponentContext,
                          @FromEvent text: String) {
        this.editPublisher.onNext(text)
        // viewModel.editUsernamePublisher.onNext(text)
    }

}