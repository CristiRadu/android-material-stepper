package com.stepstone.stepper.sample.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.sample.R
import com.stepstone.stepper.sample.step.fragment.StepperFeedbackStepFragment
import com.stepstone.stepper.viewmodel.StepViewModel

class StepperFeedbackFragmentStepAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    context: Context
) : AbstractFragmentStepAdapter(fm, lifecycle, context) {

    override fun createStep(position: Int): Step {
        return StepperFeedbackStepFragment.newInstance(position)
    }

    override fun getViewModel(position: Int): StepViewModel {
        return StepViewModel.Builder(context)
            .setTitle(R.string.tab_title)
            .create()
    }

    override fun getCount(): Int {
        return 3
    }
}