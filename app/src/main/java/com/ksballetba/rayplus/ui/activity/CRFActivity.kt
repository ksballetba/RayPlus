package com.ksballetba.rayplus.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.apkfuns.logutils.LogUtils
import com.ksballetba.rayplus.R
import com.ksballetba.rayplus.ui.activity.SampleActivity.Companion.SAMPLE_ID
import com.ksballetba.rayplus.ui.activity.baseline_visit_activity.ImagingEvaluationActivity.Companion.REFRESH_IMAGING_EVALUATION_PAGE
import com.ksballetba.rayplus.ui.activity.baseline_visit_activity.PhysicalExaminationActivity
import com.ksballetba.rayplus.ui.activity.baseline_visit_activity.PhysicalExaminationActivity.Companion.REFRESH_PHYSICAL_EXAMINATION_PAGE
import com.ksballetba.rayplus.ui.activity.baseline_visit_activity.TreatmentHistoryActivity.Companion.REFRESH_TREATMENT_HISTORY_PAGE
import com.ksballetba.rayplus.ui.activity.survival_visit_activity.SurvivalVisitActivity.Companion.REFRESH_SURVIVAL_VISIT_PAGE
import com.ksballetba.rayplus.ui.activity.treatment_visit_activity.MainPhysicalSignActivity.Companion.REFRESH_MAIN_PHYSICAL_SIGN_PAGE
import com.ksballetba.rayplus.ui.adapter.ViewPagerAdapter
import com.ksballetba.rayplus.ui.fragment.BaselineVisitFragment
import com.ksballetba.rayplus.ui.fragment.ProjectSummaryFragment
import com.ksballetba.rayplus.ui.fragment.SurvivalVisitFragment
import com.ksballetba.rayplus.ui.fragment.TreatmentVisitFragment
import com.ksballetba.rayplus.ui.fragment.base_fragment.ImagingEvaluationFragment
import com.ksballetba.rayplus.ui.fragment.baseline_visit_fragment.PhysicalExaminationFragment
import com.ksballetba.rayplus.ui.fragment.baseline_visit_fragment.TreatmentHistoryFragment
import kotlinx.android.synthetic.main.activity_crf.*

class CRFActivity : AppCompatActivity() {

    lateinit var mBaselineVisitFragment:BaselineVisitFragment
    lateinit var mTreatmentVisitFragment:TreatmentVisitFragment
    lateinit var mSurvivalVisitFragment:SurvivalVisitFragment
    lateinit var mProjectSummaryFragment: ProjectSummaryFragment
    lateinit var mViewPagerAdapter: ViewPagerAdapter

    private val mFragmentList = ArrayList<Fragment>()

    var mSampleId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimary)
        setContentView(R.layout.activity_crf)
        initUI()
        initFragment()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        when(intent?.action){
            REFRESH_PHYSICAL_EXAMINATION_PAGE->{
                val peFragment = (mViewPagerAdapter.getFragmentByIdx(0) as BaselineVisitFragment).mViewPagerAdapter.getFragmentByIdx(2) as PhysicalExaminationFragment
                peFragment.loadData()
            }
            REFRESH_IMAGING_EVALUATION_PAGE->{
                val ieFragment = (mViewPagerAdapter.getFragmentByIdx(0) as BaselineVisitFragment).mViewPagerAdapter.getFragmentByIdx(7) as ImagingEvaluationFragment
                ieFragment.loadData()
            }
            REFRESH_SURVIVAL_VISIT_PAGE->{
                val svFragment = mViewPagerAdapter.getFragmentByIdx(2) as SurvivalVisitFragment
                svFragment.loadData()
            }
            REFRESH_TREATMENT_HISTORY_PAGE->{
                val thFragment = (mViewPagerAdapter.getFragmentByIdx(0) as BaselineVisitFragment).mViewPagerAdapter.getFragmentByIdx(5) as TreatmentHistoryFragment
                thFragment.loadData()
            }
        }
    }

    private fun initUI(){
        setSupportActionBar(tb_crf)
        supportActionBar?.title = "基线资料"
        mSampleId = intent.getIntExtra(SAMPLE_ID,0)
        bnv_crf.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_baseline_visit -> {
                    supportActionBar?.title = "基线资料"
                    nsvp_crf.currentItem = 0
                }
                R.id.menu_treatment_visit -> {
                    supportActionBar?.title = "治疗期随访"
                    nsvp_crf.currentItem = 1
                }
                R.id.menu_survival_visit -> {
                    supportActionBar?.title = "生存期随访"
                    nsvp_crf.currentItem = 2
                }
                R.id.menu_project_summary->{
                    supportActionBar?.title = "项目总结"
                    nsvp_crf.currentItem = 3
                }
            }
            true
        }
    }

    private fun initFragment(){
        val sampleIdArgs = Bundle()
        sampleIdArgs.putInt(SAMPLE_ID, mSampleId)
        LogUtils.tag("sampleID").d(mSampleId)
        mBaselineVisitFragment = BaselineVisitFragment()
        mTreatmentVisitFragment = TreatmentVisitFragment()
        mSurvivalVisitFragment = SurvivalVisitFragment()
        mProjectSummaryFragment = ProjectSummaryFragment()
        mBaselineVisitFragment.arguments = sampleIdArgs
        mTreatmentVisitFragment.arguments = sampleIdArgs
        mSurvivalVisitFragment.arguments = sampleIdArgs
        mProjectSummaryFragment.arguments = sampleIdArgs
        mFragmentList.add(mBaselineVisitFragment)
        mFragmentList.add(mTreatmentVisitFragment)
        mFragmentList.add(mSurvivalVisitFragment)
        mFragmentList.add(mProjectSummaryFragment)
        nsvp_crf.offscreenPageLimit = 4
        mViewPagerAdapter = ViewPagerAdapter(mFragmentList,supportFragmentManager)
        nsvp_crf.adapter = mViewPagerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
