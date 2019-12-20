package com.ksballetba.rayplus.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.ksballetba.rayplus.R
import com.ksballetba.rayplus.ui.activity.SampleActivity.Companion.SAMPLE_ID
import com.ksballetba.rayplus.ui.adapter.ViewPagerAdapter
import com.ksballetba.rayplus.ui.fragment.base_fragment.ImagingEvaluationFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.InvestigatorSignatureFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.LabInspectionFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.VisitTimeFragment
import com.ksballetba.rayplus.ui.fragment.baseline_visit_fragment.*
import kotlinx.android.synthetic.main.fragment_baseline_visit.*

/**
 * A simple [Fragment] subclass.
 */
class BaselineVisitFragment : Fragment() {

    companion object{
        const val BASELINE_CYCLE_NUMBER = 1
        const val BASELINE_CYCLE_NUMBER_KEY = "BASELINE_CYCLE_NUMBER_KEY"
    }

    lateinit var mViewPagerAdapter: ViewPagerAdapter

    private val mFragmentList = mutableListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_baseline_visit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragments()
    }

    private fun initFragments(){
        val sampleId = if(arguments!=null) (arguments as Bundle).getInt(SAMPLE_ID) else 0
        val baselineCycleNumberArgs = Bundle()
        baselineCycleNumberArgs.putInt(BASELINE_CYCLE_NUMBER_KEY, BASELINE_CYCLE_NUMBER)
        baselineCycleNumberArgs.putInt(SAMPLE_ID, sampleId)
        val visitTimeFragment = VisitTimeFragment()
        val demographicsFragment = DemographicsFragment()
        val physicalExaminationFragment = PhysicalExaminationFragment()
        val previousHistoryFragment = PreviousHistoryFragment()
        visitTimeFragment.arguments = baselineCycleNumberArgs
        demographicsFragment.arguments = baselineCycleNumberArgs
        physicalExaminationFragment.arguments = baselineCycleNumberArgs
        previousHistoryFragment.arguments = baselineCycleNumberArgs
        mFragmentList.add(visitTimeFragment)
        mFragmentList.add(demographicsFragment)
        mFragmentList.add(physicalExaminationFragment)
        mFragmentList.add(previousHistoryFragment)
        mFragmentList.add(FirstVisitProcessFragment())
        mFragmentList.add(TreatmentHistoryFragment())
        mFragmentList.add(LabInspectionFragment())
        mFragmentList.add(ImagingEvaluationFragment())
        mFragmentList.add(InvestigatorSignatureFragment())
        mViewPagerAdapter = ViewPagerAdapter(mFragmentList,childFragmentManager)
        vp_baseline_visit.adapter = mViewPagerAdapter
        vp_baseline_visit.offscreenPageLimit = 3
        tl_baseline_visit.setupWithViewPager(vp_baseline_visit)
        tl_baseline_visit.getTabAt(0)?.text = "访视时间"
        tl_baseline_visit.getTabAt(1)?.text = "人口统计学"
        tl_baseline_visit.getTabAt(2)?.text = "体格检查"
        tl_baseline_visit.getTabAt(3)?.text = "既往史"
        tl_baseline_visit.getTabAt(4)?.text = "初诊过程"
        tl_baseline_visit.getTabAt(5)?.text = "治疗史"
        tl_baseline_visit.getTabAt(6)?.text = "实验室检查"
        tl_baseline_visit.getTabAt(7)?.text = "影像学评估"
        tl_baseline_visit.getTabAt(8)?.text = "研究者签字"
    }

}
