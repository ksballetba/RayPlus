package com.ksballetba.rayplus.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ksballetba.rayplus.R

class VisitsAdapter(layoutResId:Int,data:List<String>): BaseQuickAdapter<String, BaseViewHolder>(layoutResId,data) {

    companion object {
        const val TAG = "ProjectsAdapter"
    }

    override fun convert(helper: BaseViewHolder, item: String?) {
        helper.setText(R.id.tv_visit_name,item)
    }
}