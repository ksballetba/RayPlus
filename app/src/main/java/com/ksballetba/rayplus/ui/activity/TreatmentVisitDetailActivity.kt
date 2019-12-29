package com.ksballetba.rayplus.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ksballetba.rayplus.R
import com.ksballetba.rayplus.ui.activity.SampleActivity.Companion.SAMPLE_ID
import com.ksballetba.rayplus.ui.adapter.ViewPagerAdapter
import com.ksballetba.rayplus.ui.fragment.BaselineVisitFragment.Companion.CYCLE_NUMBER_KEY
import com.ksballetba.rayplus.ui.fragment.TreatmentVisitFragment.Companion.TREATMENT_CYCLE_NUMBER_KEY
import com.ksballetba.rayplus.ui.fragment.TreatmentVisitFragment.Companion.VISIT_TITLE
import com.ksballetba.rayplus.ui.fragment.base_fragment.ImagingEvaluationFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.InvestigatorSignatureFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.LabInspectionFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.VisitTimeFragment
import com.ksballetba.rayplus.ui.fragment.treatment_visit_fragment.AdverseEventFragment
import com.ksballetba.rayplus.ui.fragment.treatment_visit_fragment.MainPhysicalSignFragment
import com.ksballetba.rayplus.ui.fragment.treatment_visit_fragment.TherapeuticEvaluationFragment
import com.ksballetba.rayplus.ui.fragment.treatment_visit_fragment.TreatmentRecordFragment
import kotlinx.android.synthetic.main.activity_treatment_visit_detail.*

class TreatmentVisitDetailActivity : AppCompatActivity() {

    private val mFragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment_visit_detail)
        initUI()
        initFragments()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUI(){
        setSupportActionBar(tb_treatment_visit)
        val title = intent?.getStringExtra(VISIT_TITLE)
        supportActionBar?.title = title
    }

    private fun initFragments(){
        val sampleId  = intent.getIntExtra(SAMPLE_ID,0)
        val cycleNumber  = intent.getIntExtra(TREATMENT_CYCLE_NUMBER_KEY,0)
        val treatmentArgs = Bundle()
        treatmentArgs.putInt(CYCLE_NUMBER_KEY, cycleNumber)
        treatmentArgs.putInt(SAMPLE_ID, sampleId)
        val visitTimeFragment = VisitTimeFragment()
        val mainPhysicalSignFragment = MainPhysicalSignFragment()
        val imagingEvaluationFragment = ImagingEvaluationFragment()
        val therapeuticEvaluationFragment = TherapeuticEvaluationFragment()
        val labInspectionFragment = LabInspectionFragment()
        val treatmentRecordFragment = TreatmentRecordFragment()
        val adverseEventFragment = AdverseEventFragment()
        val investigatorSignatureFragment = InvestigatorSignatureFragment()
        visitTimeFragment.arguments = treatmentArgs
        mainPhysicalSignFragment.arguments = treatmentArgs
        imagingEvaluationFragment.arguments = treatmentArgs
        therapeuticEvaluationFragment.arguments = treatmentArgs
        labInspectionFragment.arguments = treatmentArgs
        treatmentRecordFragment.arguments = treatmentArgs
        adverseEventFragment.arguments = treatmentArgs
        investigatorSignatureFragment.arguments = treatmentArgs
        mFragmentList.add(visitTimeFragment)
        mFragmentList.add(mainPhysicalSignFragment)
        mFragmentList.add(imagingEvaluationFragment)
        mFragmentList.add(therapeuticEvaluationFragment)
        mFragmentList.add(labInspectionFragment)
        mFragmentList.add(treatmentRecordFragment)
        mFragmentList.add(adverseEventFragment)
        mFragmentList.add(investigatorSignatureFragment)
        vp_treatment_visit.adapter = ViewPagerAdapter(mFragmentList,supportFragmentManager)
        vp_treatment_visit.offscreenPageLimit = 3
        tl_treatment_visit.setupWithViewPager(vp_treatment_visit)
        tl_treatment_visit.getTabAt(0)?.text = "访视时间"
        tl_treatment_visit.getTabAt(1)?.text = "主要症状体征"
        tl_treatment_visit.getTabAt(2)?.text = "影像学评估"
        tl_treatment_visit.getTabAt(3)?.text = "疗效评价"
        tl_treatment_visit.getTabAt(4)?.text = "实验室检查"
        tl_treatment_visit.getTabAt(5)?.text = "治疗记录单"
        tl_treatment_visit.getTabAt(6)?.text = "不良事件"
        tl_treatment_visit.getTabAt(7)?.text = "研究者签字"
    }
}
