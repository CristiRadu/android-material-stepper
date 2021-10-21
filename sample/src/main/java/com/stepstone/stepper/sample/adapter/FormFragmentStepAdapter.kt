package com.stepstone.stepper.sample.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.sample.step.fragment.FormStepFragment

class FormFragmentStepAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    context: Context
) : AbstractFragmentStepAdapter(fm, lifecycle, context) {

    override fun createStep(position: Int): Step {
        return FormStepFragment.newInstance()
    }

    override fun getCount(): Int {
        return 3
    }
}