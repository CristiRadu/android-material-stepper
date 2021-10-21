//package com.stepstone.stepper.sample.adapter
//
//import android.content.Context
//import android.support.annotation.IntRange
//import android.util.SparseArray
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.lifecycle.Lifecycle
//
//import com.stepstone.stepper.Step
//import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
//import com.stepstone.stepper.adapter.AbstractStepAdapter
//import com.stepstone.stepper.sample.R
//import com.stepstone.stepper.sample.step.view.StepViewSample
//import com.stepstone.stepper.viewmodel.StepViewModel
//
///**
// * A naive implementation of [AbstractStepAdapter].
// * This does not keep the view data on rotation, etc.
// * It also keeps a reference to the created views.
// */
//class SampleStepAdapter(fm: FragmentManager,
//                        lifecycle: Lifecycle,
//                        context: Context
//) : AbstractStepAdapter(fm, lifecycle, context) {
//
//    private val pages = SparseArray<Step>()
//
//    override fun createStep(position: Int): StepViewSample {
//        return StepViewSample(context)
//    }
//
//    override fun getViewModel(position: Int): StepViewModel {
//        return StepViewModel.Builder(context)
//                .setTitle(R.string.tab_title)
//                .create()
//    }
//
//    override fun getCount(): Int {
//        return 3
//    }
//
//    override fun findStep(position: Int): Step? {
//        return if (pages.size() > 0) pages.get(position) else null
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        TODO("Not yet implemented")
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): View {
//        var step: Step? = pages.get(position)
//        if (step == null) {
//            step = createStep(position)
//            pages.put(position, step)
//        }
//
//        val stepView = step as View
//        container.addView(stepView)
//
//        return stepView
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view === `object`
//    }
//}
