package com.xxunghee.animationexample

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ZoomFragment : Fragment() {
    private lateinit var ivOrigin: ImageView
    private lateinit var ivZoom:   ImageView

    private var animator: Animator? = null
    private var animDuration: Long = 5000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_zoom, container, false)

        ivOrigin = root.findViewById(R.id.iv_origin)
        ivOrigin.setOnClickListener {
            zoomImage()
        }

        ivZoom = root.findViewById(R.id.iv_zoom)
        return root
    }

    /**
     * [참고 link](https://developer.android.com/training/animation/zoom)
     */
    private fun zoomImage() {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        animator?.cancel()

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        ivOrigin.getGlobalVisibleRect(startBoundsInt)

        ivOrigin.rootView.getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        ivOrigin.alpha = 0f
        ivZoom.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        ivZoom.pivotX = 0f
        ivZoom.pivotY = 0f

        // TODO: zoom 될 때 Y 방향으로 움직이는 현상 수정
        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        animator = AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(
                ivZoom,
                View.X,
                startBounds.left,
                finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(ivZoom, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(ivZoom, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(ivZoom, View.SCALE_Y, startScale, 1f))
            }
            duration = animDuration
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    animator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    animator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        ivZoom.setOnClickListener {
            animator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            animator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(ivZoom, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(ivZoom, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(ivZoom, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(ivZoom, View.SCALE_Y, startScale))
                }
                duration = animDuration
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        ivOrigin.alpha = 1f
                        ivZoom.visibility = View.GONE
                        animator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        ivOrigin.alpha = 1f
                        ivZoom.visibility = View.GONE
                        animator = null
                    }
                })
                start()
            }
        }
    }
}