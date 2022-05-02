package com.pichurchyk.softCorp.ui.splash.util

import androidx.constraintlayout.motion.widget.MotionLayout

class AnimationListener(private val completeListener: CompleteListener) :
    MotionLayout.TransitionListener {
    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}

    override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        completeListener.transitionComplete()
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
    }

    interface CompleteListener {
        fun transitionComplete()
    }
}