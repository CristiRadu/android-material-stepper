package com.stepstone.stepper.internal.util

import android.view.View

val View?.isGone : Boolean
    get() = this?.visibility == View.GONE