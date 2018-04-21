package com.gitrepo.geektree.lithogitrepo.Views

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.RoundingParams
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.Prop
import com.facebook.litho.fresco.FrescoImage

object ProfileImageSpec {
    private val roundAttribute: RoundingParams by lazy {
        RoundingParams.asCircle()
                .setBorderColor(Color.DKGRAY)
                .setBorderWidth(5.0f)
    }

    fun spec(c: ComponentContext, @Prop profileURL: String): FrescoImage.Builder {
        return Fresco.newDraweeControllerBuilder()
                .setUri(profileURL)
                .build().let {
                    FrescoImage.create(c)
                            .controller(it)
                            .imageAspectRatio(1.0f)
                            .placeholderImage(this.placeholderImage())
                            .maxHeightPx(150)
                            .maxWidthPx(150)
                            .minHeightPx(150)
                            .minWidthPx(150)
                            .aspectRatio(1.0f)
                            .actualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                            .roundingParams(this.roundAttribute)
                }
    }

    fun placeholderImage(): Drawable {
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.paint.color = Color.LTGRAY
        return shapeDrawable
    }
}