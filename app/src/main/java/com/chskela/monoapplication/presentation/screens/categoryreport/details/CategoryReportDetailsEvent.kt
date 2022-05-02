package com.chskela.monoapplication.presentation.screens.categoryreport.details

sealed class CategoryReportDetailsEvent{
    data class SelectTab(val tab: Int) : CategoryReportDetailsEvent()
    data class GetCategory(val categoryId: Long) : CategoryReportDetailsEvent()
}
