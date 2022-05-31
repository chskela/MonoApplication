package com.chskela.monoapplication.presentation.screens.details

sealed class CategoryReportDetailsEvent{
    data class SelectTab(val tab: Int) : CategoryReportDetailsEvent()
    data class GetData(val categoryId: Long) : CategoryReportDetailsEvent()
}
