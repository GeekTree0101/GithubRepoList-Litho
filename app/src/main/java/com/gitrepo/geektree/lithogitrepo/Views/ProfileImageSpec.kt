package com.gitrepo.geektree.lithogitrepo.Views
import android.graphics.Color
import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.RoundingParams
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.litho.Border
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.fresco.FrescoImage
import com.facebook.yoga.YogaEdge

@LayoutSpec
object ProfileImageSpec {

    private val roudingAttribute: RoundingParams by lazy {
        RoundingParams.asCircle()
                .setBorderColor(Color.GRAY)
                .setBorderWidth(20.0f)
    }

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop profileURL: String): Component {

        return Fresco.newDraweeControllerBuilder()
                .setUri(profileURL)
                .build().let {
                    FrescoImage.create(c)
                            .controller(it)
                            .imageAspectRatio(1.0f)
                            .widthPx(500)
                            .heightPx(500)
                            .roundingParams(this.roudingAttribute)
                            .build()
                }
    }
}