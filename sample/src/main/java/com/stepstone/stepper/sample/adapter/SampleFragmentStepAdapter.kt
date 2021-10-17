package com.stepstone.stepper.sample.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.sample.R
import com.stepstone.stepper.sample.step.fragment.StepFragmentSample
import com.stepstone.stepper.viewmodel.StepViewModel

class SampleFragmentStepAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    context: Context
) : AbstractFragmentStepAdapter(fm, lifecycle, context) {

    override fun getViewModel(position: Int): StepViewModel {
        val builder = StepViewModel.Builder(context)
            .setTitle(R.string.tab_title)
        if (position == 1) {
            builder.setSubtitle("Optional")
        }
        return builder
            .create()
    }

    override fun createStep(position: Int): Step {
        when (position) {
            0 -> return StepFragmentSample.newInstance(R.layout.fragment_step)
            1 -> return StepFragmentSample.newInstance(R.layout.fragment_step2)
            2 -> return StepFragmentSample.newInstance(R.layout.fragment_step3)
            else -> throw IllegalArgumentException("Unsupported position: " + position)
        }
    }

    override fun getCount(): Int {
        return 3
    }
}