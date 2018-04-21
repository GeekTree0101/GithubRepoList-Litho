package com.gitrepo.geektree.lithogitrepo.Views
import android.graphics.Color
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.RoundingParams
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.fresco.FrescoImage

@LayoutSpec
object ProfileImageSpec {
    private val roundAttribute: RoundingParams by lazy {
        RoundingParams.asCircle()
                .setBorderColor(Color.BLUE)
                .setBorderWidth(2.0f)
    }

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop profileURL: String): Component {
        return Fresco.newDraweeControllerBuilder()
                .setUri(profileURL)
                .build().let {
                    FrescoImage.create(c)
                            .controller(it)
                            .imageAspectRatio(1.0f)
                            .widthPx(150)
                            .heightPx(150)
                            .roundingParams(this.roundAttribute)
                            .build()
                }
    }
}