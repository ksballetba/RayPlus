package com.ksballetba.rayplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksballetba.rayplus.data.bean.*
import com.ksballetba.rayplus.data.source.remote.TreatmentVisitDataSource

class
TreatmentVisitViewModel constructor(private var treatmentVisitDataSource: TreatmentVisitDataSource) : ViewModel() {

    fun getNavigation(sampleId: Int): LiveData<List<NavigationBean.Data>> {
        val result = MutableLiveData<List<NavigationBean.Data>>()
        treatmentVisitDataSource.getNavigation(sampleId) {
            result.postValue(it)
        }
        return result
    }

    fun addCycle(sampleId: Int): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.addCycle(sampleId) {
            result.postValue(it)
        }
        return result
    }

    fun deleteCycle(sampleId: Int): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.deleteCycle(sampleId) {
            result.postValue(it)
        }
        return result
    }

    fun getMainPhysicalSignList(sampleId: Int,cycleNumber:Int): LiveData<List<MainPhysicalSignListBean.Data>> {
        val result = MutableLiveData<List<MainPhysicalSignListBean.Data>>()
        treatmentVisitDataSource.getMainPhysicalSignList(sampleId,cycleNumber) {
            result.postValue(it)
        }
        return result
    }

    fun editMainPhysicalSign(sampleId: Int,cycleNumber:Int,mainPhysicalSignBodyBean: MainPhysicalSignBodyBean): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.editMainPhysicalSign(sampleId,cycleNumber,mainPhysicalSignBodyBean) {
            result.postValue(it)
        }
        return result
    }

    fun deleteMainPhysicalSign(sampleId: Int,cycleNumber:Int,mainSymptomId:Int): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.deleteMainPhysicalSign(sampleId,cycleNumber,mainSymptomId) {
            result.postValue(it)
        }
        return result
    }

    fun getECOGScore(sampleId: Int,cycleNumber:Int): LiveData<ECOGBean> {
        val result = MutableLiveData<ECOGBean>()
        treatmentVisitDataSource.getECOGScore(sampleId,cycleNumber) {
            result.postValue(it)
        }
        return result
    }

    fun editECOGScore(sampleId: Int,cycleNumber:Int,ecogBean: ECOGBean): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.editECOGScore(sampleId,cycleNumber,ecogBean) {
            result.postValue(it)
        }
        return result
    }

    fun getTherapeuticEvaluation(sampleId: Int,cycleNumber:Int): LiveData<TherapeuticEvaluationBean> {
        val result = MutableLiveData<TherapeuticEvaluationBean>()
        treatmentVisitDataSource.getTherapeuticEvaluation(sampleId,cycleNumber) {
            result.postValue(it)
        }
        return result
    }

    fun editTherapeuticEvaluation(sampleId: Int,cycleNumber:Int,therapeuticEvaluationBean: TherapeuticEvaluationBean): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.editTherapeuticEvaluation(sampleId,cycleNumber,therapeuticEvaluationBean) {
            result.postValue(it)
        }
        return result
    }

    fun getTreatmentRecordList(sampleId: Int,cycleNumber:Int): LiveData<List<TreatmentRecordListBean.Data>> {
        val result = MutableLiveData<List<TreatmentRecordListBean.Data>>()
        treatmentVisitDataSource.getTreatmentRecordList(sampleId,cycleNumber) {
            result.postValue(it)
        }
        return result
    }

    fun editTreatmentRecord(sampleId: Int,cycleNumber:Int,treatmentRecordBodyBean: TreatmentRecordBodyBean): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.editTreatmentRecord(sampleId,cycleNumber,treatmentRecordBodyBean) {
            result.postValue(it)
        }
        return result
    }

    fun deleteTreatmentRecord(sampleId: Int,cycleNumber:Int,treatmentRecordId:Int): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.deleteTreatmentRecord(sampleId,cycleNumber,treatmentRecordId) {
            result.postValue(it)
        }
        return result
    }

    fun getAdjustment(sampleId: Int,cycleNumber:Int): LiveData<AdjustBean> {
        val result = MutableLiveData<AdjustBean>()
        treatmentVisitDataSource.getAdjustment(sampleId,cycleNumber) {
            result.postValue(it)
        }
        return result
    }

    fun editAdjustment(sampleId: Int,cycleNumber:Int,adjustBodyBean:AdjustBodyBean): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.editAdjustment(sampleId,cycleNumber,adjustBodyBean) {
            result.postValue(it)
        }
        return result
    }

    fun getAdverseEventList(sampleId: Int,cycleNumber:Int): LiveData<List<AdverseEventListBean.Data>> {
        val result = MutableLiveData<List<AdverseEventListBean.Data>>()
        treatmentVisitDataSource.getAdverseEventList(sampleId,cycleNumber) {
            result.postValue(it)
        }
        return result
    }

    fun editAdverseEvent(sampleId: Int,cycleNumber:Int,adverseEventBodyBean: AdverseEventBodyBean): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.editAdverseEvent(sampleId,cycleNumber,adverseEventBodyBean) {
            result.postValue(it)
        }
        return result
    }

    fun deleteAdverseEvent(sampleId: Int,cycleNumber:Int,adverseEventId:Int): LiveData<BaseResponseBean> {
        val result = MutableLiveData<BaseResponseBean>()
        treatmentVisitDataSource.deleteAdverseEvent(sampleId,cycleNumber,adverseEventId) {
            result.postValue(it)
        }
        return result
    }

    fun getLoadStatus() = treatmentVisitDataSource.mLoadStatus
}