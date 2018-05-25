package com.gitrepo.geektree.lithogitrepo.Views

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.RoundingParams
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.fresco.FrescoImage
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

@LayoutSpec
object ProfileImageViewSpec {
    private const val defaultSize: Int = 150

    private val placeholderImage: Drawable by lazy {
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.paint.color = Color.LTGRAY
        shapeDrawable
    }

    private val disposeBag = CompositeDisposable()

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext,
                       @Prop urlBinder: Observable<String>,
                       @Prop(optional = true) defaultScale: Int?): Component {
        val size = this.defaultSize * (defaultScale ?: 1)
        val imageView = Fresco.newDraweeControllerBuilder()

        val urlDisposable = urlBinder.subscribeBy(onNext = {
            imageView.setUri(it)
        })
        this.disposeBag.add(urlDisposable)

        return imageView.build().let {
                    FrescoImage.create(c)
                            .controller(it)
                            .imageAspectRatio(1.0f)
                            .aspectRatio(1.0f)
                            .placeholderImage(this.placeholderImage)
                            .maxHeightPx(size)
                            .maxWidthPx(size)
                            .minHeightPx(size)
                            .minWidthPx(size)
                            .actualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                            .roundingParams(this.roundAttribute((defaultScale?: 1).toFloat()))
                            .build()
                }
    }

    private fun roundAttribute(scale: Float): RoundingParams {
        return RoundingParams.asCircle()
                .setBorderColor(Color.DKGRAY)
                .setBorderWidth(5.0f * scale)
    }
}