package com.ksballetba.rayplus.ui.activity.survival_visit_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ksballetba.rayplus.R
import com.ksballetba.rayplus.data.bean.SurvivalVisitBodyBean
import com.ksballetba.rayplus.ui.fragment.SurvivalVisitFragment.Companion.SURVIVAL_VISIT_BODY
import com.ksballetba.rayplus.util.getInterviewWay
import com.ksballetba.rayplus.util.getOSMethod
import com.ksballetba.rayplus.util.getSurvivalStatus
import com.ksballetba.rayplus.util.getSurvivalVisitViewModel
import com.ksballetba.rayplus.viewmodel.SurvivalVisitViewModel
import com.lxj.xpopup.XPopup
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_survival_visit.*
import java.util.*

class SurvivalVisitActivity : AppCompatActivity() {

    companion object {
        const val REFRESH_SURVIVAL_VISIT_PAGE = "REFRESH_SURVIVAL_VISIT_PAGE"
    }

    lateinit var mViewModel:SurvivalVisitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survival_visit)
        initUI()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUI() {
        setSupportActionBar(tb_survival_visit)
        mViewModel = getSurvivalVisitViewModel(this)
        val survivalVisitBody = intent.getParcelableExtra<SurvivalVisitBodyBean>(SURVIVAL_VISIT_BODY)
        if(survivalVisitBody!=null){
            loadData(survivalVisitBody)
        }
        cl_visit_date.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                { _, year, monthOfYear, dayOfMonth ->
                    val date = "$year-$monthOfYear-$dayOfMonth"
                    tv_visit_date.text = date
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show(supportFragmentManager, "请选择日期")
        }
        cl_visit_way.setOnClickListener {
            XPopup.Builder(this)
                .asCenterList(getString(R.string.visit_way), arrayOf("电话", "门诊", "住院")) { _, text ->
                    tv_visit_way.text = text
                }.show()
        }
        cl_anti_tumor_treat.setOnClickListener {
            XPopup.Builder(this)
                .asCenterList(getString(R.string.anti_tumor_treat), arrayOf("是", "否")) { _, text ->
                    tv_anti_tumor_treat.text = text
                }.show()
        }
        cl_live_status.setOnClickListener {
            XPopup.Builder(this).asCenterList(
                getString(R.string.live_status),
                getSurvivalStatus()
            ) { _, text ->
                tv_live_status.text = text
            }.show()
        }
        cl_death_date.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                { _, year, monthOfYear, dayOfMonth ->
                    val date = "$year-$monthOfYear-$dayOfMonth"
                    tv_death_date.text = date
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show(supportFragmentManager, "请选择日期")
        }
        cl_death_cause.setOnClickListener {
            XPopup.Builder(this)
                .asCenterList(getString(R.string.death_cause), arrayOf("疾病进展", "其他")) { pos, text ->
                    if (pos < 1) {
                        tv_death_cause.text = text
                    } else {
                        XPopup.Builder(this)
                            .asInputConfirm(getString(R.string.death_cause), "请输入其他原因") {
                                tv_death_cause.text = it
                            }.show()
                    }
                }.show()
        }
        cl_gather_way.setOnClickListener {
            XPopup.Builder(this)
                .asCenterList(
                    getString(R.string.gather_way),
                    arrayOf(
                        "1.街道办开具死亡证明",
                        "2.民政局系统出具死亡证明",
                        "3.公安局及派出所出具死亡证明",
                        "4.火化证明或公墓数据",
                        "5.本院死亡的医疗文件",
                        "6.其他医院死亡的医疗文件",
                        "7.家属手写证明文件",
                        "8.电话随访获知"
                    )
                ) { pos, text ->
                    if (pos < 8) {
                        tv_gather_way.text = text
                    } else {
                        XPopup.Builder(this)
                            .asInputConfirm(getString(R.string.gather_way), "请输入其他收集形式") {
                                tv_gather_way.text = it
                            }.show()
                    }
                }.show()
        }
        cl_confirm_live_date.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                { _, year, monthOfYear, dayOfMonth ->
                    val date = "$year-$monthOfYear-$dayOfMonth"
                    tv_confirm_live_date.text = date
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show(supportFragmentManager, "请选择日期")
        }
        cl_last_contact_date.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                { _, year, monthOfYear, dayOfMonth ->
                    val date = "$year-$monthOfYear-$dayOfMonth"
                    tv_last_contact_date.text = date
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show(supportFragmentManager, "请选择日期")
        }
    }

    private fun loadData(survivalVisitBody: SurvivalVisitBodyBean) {
        tv_visit_date.text = survivalVisitBody.interviewTime
        if(survivalVisitBody.interviewWay!=null){
            tv_visit_way.text = getInterviewWay()[survivalVisitBody.interviewWay]
        }
        if(survivalVisitBody.hasOtherTreatment!=null){
            tv_anti_tumor_treat.text = if(survivalVisitBody.hasOtherTreatment==1) "是" else "否"
        }
        if(survivalVisitBody.survivalStatus!=null){
            tv_live_status.text = getSurvivalStatus()[survivalVisitBody.survivalStatus]
        }
        tv_death_date.text = survivalVisitBody.dieTime
        if(survivalVisitBody.dieReason!=null){
            tv_death_cause.text = if(survivalVisitBody.dieReason<1) "疾病进展" else survivalVisitBody.otherReason
        }
        if(survivalVisitBody.oSMethod!=null){
            tv_gather_way.text = if(survivalVisitBody.oSMethod<8) getOSMethod()[survivalVisitBody.oSMethod] else survivalVisitBody.otherMethod
        }
        tv_confirm_live_date.text = survivalVisitBody.statusConfirmTime
        tv_last_contact_date.text = survivalVisitBody.lastTimeSurvival
    }
}
